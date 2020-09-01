package com.qa.hubspot.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pages.SalesPage;

public class BaseTest {

	
	WebDriver driver;
	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public ContactsPage contactsPage;
	public SalesPage salesPage;
	
	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName) {
		System.out.println("Brwoser Name is: "+browserName);
		basePage= new BasePage();
		prop = basePage.init_prop();
		prop.setProperty("browser", browserName); //at Run Time config file take the browser from parameter from testng.xml 
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		
	}
	
	
//if we want to run individual class (not from testng.xml), uncomment this comment above one and execute OR create testng.xml for that individual class and execute	
//	@BeforeTest
//	public void setUp() {
//		
//		basePage= new BasePage();
//		prop = basePage.init_prop();
//		driver = basePage.init_driver(prop);
//		loginPage = new LoginPage(driver);
//		
//	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
