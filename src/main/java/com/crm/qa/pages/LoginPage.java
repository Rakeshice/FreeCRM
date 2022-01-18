package com.crm.qa.pages;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(xpath = "//input[@name='username']")
	WebElement userName;

	By userPassword = By.name("password");

	@FindBy(css = "input.btn.btn-small")
	WebElement loginButton;

	@FindBy(css = "a[href='https://classic.freecrm.com/register/']")
	WebElement SignUpLink;

	@FindBy(css = "a.navbar-brand")
	WebElement CRMPROLogo;

	public LoginPage() throws FileNotFoundException {
		 //super();
		PageFactory.initElements(getdriver(), this);
		
		// TODO Auto-generated constructor stub
	}

	public HomePage login(String uName, String uPass) throws FileNotFoundException {
		userName.sendKeys(uName);
		getdriver().findElement(userPassword).sendKeys(uPass);
		JavascriptExecutor js= (JavascriptExecutor)getdriver();
		js.executeScript("arguments[0].click();", loginButton);
		//loginButton.click();
		return new HomePage();
	}

	public String validateTitle() {
		return getdriver().getTitle();

	}

	public boolean validateLogo() {
		return CRMPROLogo.isDisplayed();
	}

}
