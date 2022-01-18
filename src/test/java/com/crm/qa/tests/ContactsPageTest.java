package com.crm.qa.tests;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	TestUtil util;
	ContactsPage contactspage;
	String sheetname="Contacts";
	
	private static final Logger logger = LogManager.getLogger(TestBase.class.getName());

	public ContactsPageTest() throws FileNotFoundException {
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
		util.switchToFrame();
		contactspage = homepage.clickOnContactsLink();
	}
	
	@Test
	public void verifySelectContactsByName()
	{
		String name="Abhi Kr";
	Assert.assertTrue(contactspage.verifySelectContacts(name));	
	logger.info("verify names are selected");
	}
	
	@Test(priority=1,dataProvider="CRMfile")
	public void verifyCreateNewContact(String title,String fname, String lanme, String comp) 
	{
		homepage.clickOnnewContactLink();
		contactspage.createNewContacts(title,fname,lanme,comp);	
		Assert.assertTrue(false);
	}
	
	@DataProvider(name="CRMfile")
	public Object[][] getCRMTestData()
	{
		Object[][]data= TestUtil.excelFileData(sheetname);
		return data;
	}

	@AfterMethod
	public void tearDown() {
		getdriver().quit();
		logger.info("driver closed");
		unload();
	}

}
