package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BaseTest;
import com.qa.hubspot.listeners.TestAllureListener;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(TestAllureListener.class)
@Epic("Epic 100: design login page module for hub spot application")
@Story("US -101: design all features of login page - login module, sing up link and title")


public class LoginPageTest extends BaseTest{

	
	
	@Description("Verify login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void verifyLoginPageTitleTest() {
		String title =loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is: "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Verify sign up link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.isSignUpLinkExists());
		
	}
	@Description("Verify loogin feature test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	
	
}
