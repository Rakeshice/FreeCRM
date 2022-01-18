package com.crm.qa.tests;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage = new HomePage();
	ContactsPage contactspage;
	TestUtil util;
	private static final Logger logger = LogManager.getLogger(TestBase.class.getName());
	public HomePageTest() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setup() throws FileNotFoundException {
		initialization();
		logger.info("Driver is Initialised and browser lunched");
		loginpage = new LoginPage();
		util = new TestUtil();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("userpass"));
		logger.info("user logged into the system");
	}

	@AfterMethod
	public void tearDown() {
		getdriver().close();
		logger.info("browser closed");
		unload();
	}

	@Test(priority = 0)
	public void verifyHomePageTitle() {
		String homePageTitle = homepage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "Title doen't match");
		logger.info("Homepage title is validated");
	}

	@Test(priority = 1)
	public void clickOnContactsLink() {
		util.switchToFrame();
		contactspage = homepage.clickOnContactsLink();
		Assert.assertTrue(false);
//Assert.assertTrue(contactspage.verifyContactsLablepresent(), "Lable is not present"); 

	}
}
