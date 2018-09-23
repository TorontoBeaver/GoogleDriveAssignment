package com.qa.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Browser {

	private static int PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS = 15;
	private static int COMMAND_DEFAULT_TIMEOUT_SECONDS = 20;
	private static int WAIT_ELEMENT_TIMEOUT = 10;
	private static String SCREENSHOTS_NAME_TPL = "screenshots/scr";
	private WebDriver driver;
	private static Browser instance = null;

	private Browser(WebDriver driver) {
		this.driver = driver;
	}


	public static Browser getInstance() {

		if (instance != null) {
			return instance;
		}
		return instance = init();
	}

	private static Browser init() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver(getChromeDriverProfile() );
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		return new Browser(driver);
	}

	private static DesiredCapabilities getChromeDriverProfile() {

		HashMap<String, Object> chromePrefs = new HashMap< ~ > ();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download_default_directory", TestRunnerOptions.DOWNLOAD_DIR);
		chromePrefs.put("dounload_prompt_for_download", false);
		ChromeOptions = new ChromeOptions("prefs", chromePrefs);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return capabilities;
	}

	public void open(String url) {
		System.out.println("Going to URL: " + url);
		driver.get(url);
	}

	public static void kill() {
		if (instance != null) {
			try {
				instance.driver.quit();
			} finally {
				instance = null;
			}
		}
	}

	public void waitForElementVisible(By locator) {
		new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void highlightElement(By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid orange'", driver.findElement(locator));

	}

	public void unHighlightElement(By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
	}

	public void click(By locator) {
		//waitForElementVisible(locator);
		System.out.println("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
		highlightElement(locator);
		takeScreenshot();
		unHighlightElement(locator);
		driver.findElement(locator).click();
	}

	public void clickForTwoArrowsDown(By locator) {

		System.out.println("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
		highlightElement(locator);
		takeScreenshot();
		unHighlightElement(locator);

		driver.findElement(locator).sendKeys("src/main/java/com/qa/config/confog.properties");
		takeScreenshot();
	}

	public void rightClick(By locator) {
		waitForElementVisible(locator);
		System.out.println("Clicking right button on the element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
		highlightElement(locator);
		takeScreenshot();
		unHighlightElement(locator);
		new Actions(driver).contextClick(driver.findElement(locator)).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

	}

	public void doubleClick(By locator) {

		waitForElementVisible(locator);
		System.out.println("Double clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
		highlightElement(locator);
		takeScreenshot();
		unHighlightElement(locator);
		WebElement element = driver.findElement(locator);
		new Actions(driver).doubleClick(element).perform();
		takeScreenshot();
	}

	public void dragAndDrop(By locator, By targetLocator) {
		waitForElementVisible(locator);
		waitForElementVisible(targetLocator);
		WebElement element = driver.findElement(locator);
		WebElement target = driver.findElement(targetLocator);
		takeScreenshot();
		System.out.println("Dragging element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")" +
				"to '" + driver.findElement(targetLocator).getText() + "' (Located: " + targetLocator + ")");
		(new Actions(driver)).dragAndDrop(element, target).perform();
		takeScreenshot();
		takeScreenshot();
	}

	public void type(By locator, String text) {
		waitForElementVisible(locator);
		highlightElement(locator);
		System.out.println("Typing text '" + text + "' to input form '" + driver.findElement(locator).getAttribute("name") + "' (Located: " + locator + ")");
		driver.findElement(locator).sendKeys(text);
		takeScreenshot();
		unHighlightElement(locator);
	}

	public void takeScreenshot() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
			File copy = new File(screenshotName + ".png");
			FileUtils.copyFile(screenshot, copy);
			System.out.println("Saved screenshot: " + screenshotName);
		} catch (IOException e) {
			System.out.println("Failed to make screenshot");
		}
	}
}

