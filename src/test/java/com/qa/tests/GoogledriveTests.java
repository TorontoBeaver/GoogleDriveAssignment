package com.qa.tests;

import com.qa.base.Browser;
import com.qa.pages.HomePage;
import com.qa.pages.IntroductionPage;
import com.qa.pages.LoginPage;
import com.qa.utils.PropertyLoader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Properties;

public class GoogledriveTests {

	Properties prop = PropertyLoader.loadProps("src/main/java/com/qa/config/confog.properties");

	@Test
	public void createFolderTest() {
		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"), prop.getProperty("password"));
		new HomePage().mouseRightClick();
		new HomePage().newFolderCreation();
	}

	@Test
	public void moveFileToNewFolder() {
		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"), prop.getProperty("password"));
		new HomePage().mouseRightClick();
		new HomePage().newFolderCreation();
		new HomePage().dragAndDropeFile();
		new HomePage().openNewFolder();
		new HomePage().relocateFileFromFolderToMyDrive();
	}

	@Test
	public void openNewFolderTest() {
		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"), prop.getProperty("password"));
		new HomePage().mouseRightClick();
		new HomePage().newFolderCreation();
		new HomePage().openNewFolder();
	}

	@Test
	public void relocateFileFromFolderTest() {

		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"), prop.getProperty("password"));
		new HomePage().mouseRightClick();
		new HomePage().newFolderCreation();
		new HomePage().dragAndDropeFile();
		new HomePage().openNewFolder();
		new HomePage().relocateFileFromFolderToMyDrive();
	}

	@Test
	public void openMyDriveTest() {
		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"), prop.getProperty("password"));
		new HomePage().mouseRightClick();
		new HomePage().newFolderCreation();
		new HomePage().dragAndDropeFile();
		new HomePage().openNewFolder();
		new HomePage().relocateFileFromFolderToMyDrive();
		new HomePage().openDrive();

	}

	/*@Test
	public void loadFileTest(){
		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"), prop.getProperty("password"));
		new HomePage().uploadFile();
	}*/


	@AfterMethod(description = "close browser")
	public void kill() {
		Browser.kill();
	}
}
