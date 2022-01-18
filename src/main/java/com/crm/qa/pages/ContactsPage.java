package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),' Contacts')]")
	WebElement contactsLable;

	@FindBy(xpath = "//*[@class='select' and @name='title']")
	WebElement title;
	
	@FindBy(xpath = "//*[@id='first_name']")
	WebElement fName;
	
	@FindBy(xpath = "//*[@id='surname']")
	@CacheLookup
	WebElement lName;

	@FindBy(xpath = "//*[@class='select' and @name='title']")
	WebElement saveButton;

	public ContactsPage() {
		PageFactory.initElements(getdriver(), this);
	}

	public boolean verifyContactsLablepresent() {
		return contactsLable.isDisplayed();
	}

	public boolean verifySelectContacts(String name) {
		WebElement checkBoxforName = getdriver()
				.findElement(By.xpath("//a[text()='" + name + "']/parent::td/preceding-sibling::td/input"));
		checkBoxforName.click();
		return checkBoxforName.isSelected();
	}

	public void createNewContacts(String Title, String fname, String lname, String comp) {
		Select select = new Select(title);
		select.selectByVisibleText(Title);
		fName.sendKeys(fname);
		lName.sendKeys(lname);
		saveButton.click();

	}

}
