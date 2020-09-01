package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BaseTest;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest extends BaseTest{

	@BeforeClass
	public void homepageSetup() {
	homePage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	contactsPage = homePage.goToContactsPage();
	}
	
	
	@Test(priority=1)
	public void verifyContactsPageTitleTest() {
		String contactsPageTitle = contactsPage.getContactsPageTitle();
		System.out.println("Contacts Page Title is: "+contactsPageTitle);
		Assert.assertEquals(contactsPageTitle, Constants.CONTACTS_PAGE_TITLE);
		
	}
	@Test(priority=2)
	public void verifyContactsPageHeaderTest() {
		String contactsPageHeader =contactsPage.getContactsPageHeader();
		System.out.println("Contacts Page Header is: "+contactsPageHeader);
		Assert.assertTrue(contactsPageHeader.contains(Constants.CONTACTS_PAGE_HEADER));
		//Assert.assertEquals(contactsPageHeader, Constants.CONTACTS_PAGE_HEADER);
		
		
	}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object contactsData[][]=ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return contactsData;
	}
	
	
	@Test(priority=3, dataProvider="getContactsTestData")
	public void creatContactTest(String emailId,String firstName,String lastName,String jobTitle,String phoneNumber) {

//		String emailId =contactsPage.createContact("test123@gmail.com", "test", "Selenium", "QA-automation", "4569871235");
//		Assert.assertEquals(emailId, "test123@gmail.com");
		
		Assert.assertTrue(contactsPage.createContact(emailId, firstName, lastName, jobTitle, phoneNumber));
		
//		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
