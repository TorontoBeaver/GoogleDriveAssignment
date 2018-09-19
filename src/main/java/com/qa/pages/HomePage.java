package com.qa.pages;

import org.openqa.selenium.By;


public class HomePage extends AbstractPage {

	private static final By NEW_FOLDER_LOCATOR = By.xpath("//span[contains(text(),'NewOne')]");
	private static final By FILE_LOCATOR = By.xpath("//span[text()='Tesla.jpg']");
	private static final By GOOGLE_DRIVE_LOCATOR = By.xpath("//span[@data-tooltip='My Drive']");
	private static final By GOOGLE_DRIVE_LOCATOR2 = By.xpath("//span[@data-tooltip='My Drive']");
	private static final By GOOGLE_MIDDLE_POINT = By.xpath("//div[@data-target=\"sortSwitcherContainer\"]");
	private static final By NEW_FOLDERNAME_LOCATOR = By.xpath("(//input[@class=\"lb-k-Kk g-Gh\"])");
	private static final By BUTTON_NAME_CREATE_LOCATOR = By.xpath("//button[@name=\"ok\"]");



	public void dragAndDropeFile() {
		browser.dragAndDrop(FILE_LOCATOR, NEW_FOLDER_LOCATOR);
	}

	public void openNewFolder() {
		browser.openNewFolder(NEW_FOLDER_LOCATOR);

	}

	public void relocateFileFromFolderToMyDrive() {
		browser.dragAndDrop(FILE_LOCATOR, GOOGLE_DRIVE_LOCATOR);

	}

	public void openDrive() {
		browser.openMyDrive(GOOGLE_DRIVE_LOCATOR2);
		browser.refresh();
	}

	public void refreshPage() {
		browser.refresh();
	}

	public void mouseRightClick() {

		browser.rightClick(GOOGLE_MIDDLE_POINT);
	}

	public void newFolderCreation() {

		browser.highlightElement(NEW_FOLDERNAME_LOCATOR);
		browser.takeScreenshot();
		browser.type(NEW_FOLDERNAME_LOCATOR, prop.getProperty("newFolderName"));
		browser.highlightElement(BUTTON_NAME_CREATE_LOCATOR);
		browser.takeScreenshot();
		browser.click(BUTTON_NAME_CREATE_LOCATOR);
		browser.waitForElementVisible(NEW_FOLDER_LOCATOR);
		browser.takeScreenshot();


	}

}