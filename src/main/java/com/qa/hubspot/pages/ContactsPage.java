package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class ContactsPage extends BasePage{
	
	private WebDriver driver;
	ElementUtil elementUtil;

	private By createContactPrimary = By.xpath("//span[text()='Create contact']");
	private By header = By.cssSelector("h1[class*=IndexPageRedesignHeader]");
	private By emailId = By.xpath("//input[@data-field='email']");
	private By firstName = By.xpath("//input[@data-field='firstname']");
	private By lastName = By.xpath("//input[@data-field='lastname']");
	private By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	private By phoneNumber = By.xpath("//input[@data-field='phone']");
	//private By createContactSecondary = By.xpath("(//span[text()='Create contact'])[position()=2]");
	private By createContactSecondary = By.xpath("//button[@data-selenium-test='base-dialog-confirm-btn']");
	
	//private By contactBackLink = By.xpath("(//*[text()='Contacts'])[position()=2]");
	private By contactBackLink = By.xpath("//div[@class='display-flex']//*[text()='Contacts']");
	
	public ContactsPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
	}
	
	public String getContactsPageTitle() {
		return elementUtil.waitForTitle(Constants.CONTACTS_PAGE_TITLE, 10);
		}
	public String getContactsPageHeader() {
		elementUtil.waitForElementPresent(header, 10);
		return elementUtil.doGetText(header);
	}
	public boolean createContact(String emailId,String firstName, String lastName,String jobTitle,String phoneNumber) {
		elementUtil.clickWhenReady(createContactPrimary, 10);
//this.emailId: since  emaild name using for both By locator and String parameter,
//to differentiate/to access class level variable we use this keyword
		elementUtil.waitForElementToBeVisible(this.emailId, 10); 
		elementUtil.doSendKeys(this.emailId, emailId);
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.waitForElementToBeVisible(this.jobTitle, 10);
		elementUtil.doSendKeys(this.jobTitle, jobTitle);
		elementUtil.doSendKeys(this.phoneNumber, phoneNumber);
		
		elementUtil.clickWhenReady(createContactSecondary, 10);
		
		//String fullName = firstName+" "+lastName;
		//elementUtil.waitForElementToBeVisible(By.xpath("(//span[text()='"+ fullName +"'])[position()=1]"), 10);
		elementUtil.waitForElementToBeVisible(By.xpath("//span[text()='"+ emailId +"']"), 10);
//		String emailtext =elementUtil.doGetText(By.xpath("//span[text()='"+ emailId +"']"));
		
		//elementUtil.waitForElementToBeVisible(By.xpath("//span[text()='"+ emailId +"']"), 10);
		//boolean flag =elementUtil.doIsDisplayed(By.xpath("//span[text()='"+ emailId +"']"));
		
		//elementUtil.waitForElementToBeVisible(By.xpath("//input[@value='"+ lastName +"']"), 10);
		//boolean flag =elementUtil.doIsDisplayed(By.xpath("//input[@value='"+ lastName +"']"));
		//span[@class='CompanyContactEditableTitle__Title-sc-1m8z8g6-0 fCTVRJ']
		//boolean flag =elementUtil.doIsDisplayed(By.xpath("(//span[text()='"+ fullName +"'])[position()=1]"));
		elementUtil.waitForElementToBeVisible(By.xpath("//span[@data-selenium-test='highlightTitle']"), 10);
		boolean flag =elementUtil.doIsDisplayed(By.xpath("//span[@data-selenium-test='highlightTitle']"));
		elementUtil.clickWhenReady(contactBackLink, 10);
		//return finalContact;
		return flag;

		
	}
	
	
	
	
	
	
	
}
