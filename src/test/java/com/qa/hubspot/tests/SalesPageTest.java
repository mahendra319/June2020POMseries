package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BaseTest;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class SalesPageTest extends BaseTest{
	
	@BeforeClass
	public void salesPageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		salesPage= homePage.goToSalesPage();
	}

	@Test(priority=1)
	public void verifySalesPageTitleTest() {
		String salesPageTitle = salesPage.getSalesPageTitle();
		System.out.println("Sales Page Title is: "+salesPageTitle);
		Assert.assertEquals(salesPageTitle, Constants.SALES_PAGE_TITLE);
	}
	@Test(priority=2)
	public void verifySalesPageHeaderTest() {
		String salesPageHeader = salesPage.getSalesPageHeader();
		System.out.println("salesPageHeader");
		Assert.assertTrue(salesPageHeader.contains(Constants.SALES_PAGE_HEADER));
	}
	
	@DataProvider
	public Object[][] getSalesDealsTestData() {
		Object salesDealsData[][] = ExcelUtil.getTestData(Constants.SALESDEALS_SHEET_NAME);
		return salesDealsData;
	}
	@Test(priority=3, dataProvider="getSalesDealsTestData")
	public void creatDealTest(String dealName, String amount, String dealType) {
		salesPage.creatDeal(dealName, amount, dealType);
	}
	
	
	
	
	
	
}
