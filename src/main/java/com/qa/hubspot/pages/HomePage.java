package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage{
	
	
	private WebDriver driver;
	ElementUtil elementUtil;
	
	//By locators or Object Repository
	
	private By header = By.tagName("h1");
	private By accountName = By.cssSelector(".account-name ");
	private By settingIcon = By.cssSelector("a#navSetting");
	private By contactsPageParentMenu=By.id("nav-primary-contacts-branch");
	private By contactsPageSubMenu= By.id("nav-secondary-contacts");
	private By salesPageMenu=By.id("nav-primary-sales-branch");
	private By dealsPageLink = By.xpath("(//a[@id='nav-secondary-deals'])[position()=1]");
	
	//constructor
	
	public HomePage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
	}
	
	//page actions
	
	public String getHomePageTitle() {
		//return driver.getTitle();
		return elementUtil.waitForTitle(Constants.HOME_PAGE_TITLE, 10);
	}
	public String getHeaderValue() {
		
//		if(driver.findElement(header).isDisplayed()) {
//			return driver.findElement(header).getText();
//		}
//		return null;
		
		if(elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}
		return null;
	}
	public String getAccountName() {
//		if(driver.findElement(accountName).isDisplayed()) {
//			return driver.findElement(accountName).getText();
//		}
//		return null;
		
		if(elementUtil.doIsDisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}
		return null;
	}
	public boolean isSettingsIconExists() {
		//return driver.findElement(settingIcon).isDisplayed();
		return elementUtil.doIsDisplayed(settingIcon);
	}
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}
	private void clickOnContacts() {
		elementUtil.waitForElementPresent(contactsPageParentMenu, 10);
		elementUtil.doClick(contactsPageParentMenu);
		elementUtil.waitForElementPresent(contactsPageSubMenu, 5);
		elementUtil.doClick(contactsPageSubMenu);
	}
	
	public SalesPage goToSalesPage() {
		clickOnDeals();
		return new SalesPage(driver);
	}
	private void clickOnDeals() {
		elementUtil.waitForElementPresent(salesPageMenu, 10);
		elementUtil.doClick(salesPageMenu);
		elementUtil.waitForElementPresent(dealsPageLink, 5);
		elementUtil.doClick(dealsPageLink);
		//driver.navigate().refresh();
		//elementUtil.getPageRefresh();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
