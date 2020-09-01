package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BaseTest;
import com.qa.hubspot.utils.Constants;

public class HomePageTest extends BaseTest{
	

	
	@BeforeClass
	public void homepageSetup() {
	homePage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title =homePage.getHomePageTitle();
		System.out.println("Home Page title is: "+title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
		
	}
	@Test (priority=2)
	public void verifyHomePageHeaderTest() {
		
		String headerText=homePage.getHeaderValue();
		System.out.println("HomePage header is: "+headerText);
		Assert.assertEquals(headerText, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyAccountNameTest() {
		String accountName =homePage.getAccountName();
		System.out.println("Account Name is: "+accountName);	
		Assert.assertEquals(accountName, prop.getProperty("accountname").trim());
	}
	@Test(priority=4)
	public void verifySettingsIconTest() {
		Assert.assertTrue(homePage.isSettingsIconExists());
	}
	
	
	
	
}
