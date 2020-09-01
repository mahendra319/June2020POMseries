package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class SalesPage extends BasePage{
	
	private WebDriver driver;
	ElementUtil elementUtil;
	
	//By locators OR object repositories
	By header = By.cssSelector("h1[class*='IndexPageRedesignHeader']");
	By createDeal = By.xpath("//span[text()='Create deal']");
	By dealName = By.xpath("//input[@data-field='dealname']");
	By amount = By.xpath("//input[@data-field='amount']");
	By dealType = By.xpath("//div[@data-selenium-test='property-input-dealtype']");
	By create = By.xpath("//span[text()='Create']");
	By dealsBackLink = By.xpath("//div[@class='display-flex']//*[text()='Deals']");
		
	//constructor
	public SalesPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
		
	}
		
	//actions
	public String getSalesPageTitle() {
		//elementUtil.getPageRefresh();
		return elementUtil.waitForTitle(Constants.SALES_PAGE_TITLE, 20);
	}
	public String getSalesPageHeader() {
		elementUtil.waitForElementToBeVisible(header, 20);
		return elementUtil.doGetText(header);
		
	}
	public boolean creatDeal(String dealName, String amount, String dealType) {
		elementUtil.clickWhenReady(createDeal, 10);
		elementUtil.waitForElementToBeVisible(this.dealName, 10);
		elementUtil.doSendKeys(this.dealName, dealName);
		elementUtil.waitForElementToBeVisible(this.amount, 10);
		elementUtil.doSendKeys(this.amount, amount);
		elementUtil.waitForElementToBeVisible(this.dealType, 15);
		//elementUtil.doSendKeys(this.dealType, dealType);
		
		//By typeOfDeal = By.xpath("//div/label[text()='"+ dealType +"']");
		//elementUtil.selectDropDownValueByVisibleText(this.dealType, dealType);
		elementUtil.selectValueFromDropdown(this.dealType, dealType);
		elementUtil.clickWhenReady(create, 10);

		elementUtil.waitForElementToBeVisible(By.xpath("//span[text()='"+ dealName +"']"), 20);
		boolean flag = elementUtil.doIsDisplayed(By.xpath("//span[text()='"+ dealName +"']"));
		//boolean flag = elementUtil.doIsDisplayed(By.xpath("(//span[@data-selenium-test='currency-component-loaded'])[position()=1]"));
		elementUtil.clickWhenReady(dealsBackLink, 20);
		
		return flag;
	}
	
	
	
	
	
	
	
	
	
}
