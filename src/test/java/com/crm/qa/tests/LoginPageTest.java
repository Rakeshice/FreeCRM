package com.crm.qa.tests;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class LoginPageTest extends TestBase {
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil util;
	private static final Logger logger = LogManager.getLogger(TestBase.class.getName());
	public LoginPageTest() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}
	@BeforeMethod
	public void setup() throws FileNotFoundException
	{
		
		initialization();
		logger.info("driver is initialized and browser launched");
		 loginpage = new LoginPage();
		 util= new TestUtil();
	}
	@AfterMethod
	public void tearDown()
	{
		getdriver().quit();
		logger.info("browser closed");
		unload();
	}
	
	@Test(priority=0)
	public void loginPageTitleTest()
	{
		String title= loginpage.validateTitle();	
		Assert.assertEquals("CRMPRO - CRM software for customer relationship management, sales, and support.", title);
		logger.info("Login page title is validated");
	}
	
	@Test(priority=1)
	public void loginPageLogoTest()
	{
		boolean flag= loginpage.validateLogo();
		
		Assert.assertTrue(flag);
		logger.info("Login page logo is validated");
	}
	@Test(priority=2)
	public void loginPageLoginTest() throws FileNotFoundException
	{
		homepage= loginpage.login(prop.getProperty("username"),prop.getProperty("userpass"));
		logger.info("user logged into the application");
		util.switchToFrame();
		boolean flag=homepage.displayedLoggedUserName();
		Assert.assertTrue(flag);
		logger.info("Logged in user is displayed in Homepage");
	}

}
