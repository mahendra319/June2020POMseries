package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{

	private WebDriver driver;
	ElementUtil elementUtil;
	
	//By locators - Object Repository
	private By email = By.id("username");
	private By password = By.id("password");
	private By loginButton = By.id("loginBtn");
	private By signupLink = By.linkText("Sign up");
	
	//constructor
	
	public LoginPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
	}
	
	//page actions
	@Step("Getting login page title")
	public String getLoginPageTitle() {
		//return driver.getTitle();
		return elementUtil.waitForTitle(Constants.LOGIN_PAGE_TITLE, 10);
	}
	@Step("getting sing up link exists on login page")
	public boolean isSignUpLinkExists() {
		
		//return driver.findElement(signupLink).isDisplayed();
		return elementUtil.doIsDisplayed(signupLink);
		
	}
	// whenever we are landing from one page to another page because of method, it is that method responsibility to return object of landing page class
	//here doLogin method is the reason to landing on home page, so it is doLogin method responsibility to return home page class object
	//this concept is called 'PageChanining'
	@Step("User login with user name: {0} and password: {1}")
	public HomePage doLogin(String un, String pwd) {
//		driver.findElement(email).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
		System.out.println("login to applicatio");
		elementUtil.doSendKeys(email, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);
	}
}
