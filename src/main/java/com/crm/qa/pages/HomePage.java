package com.crm.qa.pages;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//tbody/tr/td[contains(text(),' group automation')]")
	WebElement displayedUserLabel;
	@FindBy(xpath = "//a[contains(@title,'Contacts')]")
	WebElement contactslink;
	@FindBy(xpath = "//a[contains(@title,'Deals')]")
	WebElement dealslink;
	@FindBy(xpath = "//a[contains(@title,'Tasks')]")
	WebElement taskslink;
	@FindBy(xpath = "//*[@title='New Contact']")
	WebElement newContactLink;
	public HomePage() throws FileNotFoundException {

		PageFactory.initElements(getdriver(), this);
		// super();
		// TODO Auto-generated constructor stub
	}

	public String verifyHomePageTitle() {
		String HomePageTitle = getdriver().getTitle();
		return HomePageTitle;
	}

	public boolean displayedLoggedUserName() {

		boolean flag = displayedUserLabel.isDisplayed();
		return flag;
	}

	public ContactsPage clickOnContactsLink() {
		contactslink.click();
		return new ContactsPage();
	}
	public DealsPage clickOnDealsLink() {
		dealslink.click();
		return new DealsPage();
	}

	public TasksPage clickOnTasksLink() throws FileNotFoundException  {
		taskslink.click();
		return new TasksPage();
	}
	
	public void clickOnnewContactLink()  {
		
		Actions action = new Actions(getdriver());
		action.moveToElement(dealslink).build().perform();
		//Thread.sleep(6000);
		//WebDriverWait t = new WebDriverWait(driver, Duration.ofSeconds(20)); 
				//t.until(ExpectedConditions.visibilityOf(contactslink));  
		action.moveToElement(contactslink).build().perform();

		//WebDriverWait t = new WebDriverWait(driver, Duration.ofSeconds(20)); 
		//t.until(ExpectedConditions.visibilityOf(newContactLink));  
		//t.until(ExpectedConditions.elementToBeClickable(newContactLink));
		newContactLink.click();
	}
}
