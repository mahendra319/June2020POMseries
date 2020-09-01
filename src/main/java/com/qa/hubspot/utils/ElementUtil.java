package com.qa.hubspot.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.Base.BasePage;


public class ElementUtil {
	
	WebDriver driver;
	JavaScriptUtil jsUtil;
	
	
	
	public ElementUtil(WebDriver driver) {
		
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	
	/**
	 * this is used to create WebElement on the basis of given By locator
	 * @param locator
	 * @return WebElement
	 */
	public  WebElement getElement(By locator) {
		
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(BasePage.flashElement)) {
			jsUtil.flash(element);
		}
		return element;
	}

	/**
	 * this is used to send values into WebElement
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		
		getElement(locator).sendKeys(value);
		
	}
	
/**
 * this is used to click on WebElements
 * @param locator
 */
	public void doClick(By locator) {
		
		getElement(locator).click();
	}

	/**
	 * this method is used to send values into the WebElements using Actions class
	 * @param locator
	 * @param value
	 */
	public void doActionsSendKeys(By locator, String value) {
		
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	
	/**
	 * This method is used to click on WebElements using Actions class
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}
	
	/**
	 * this method is used to get the text from WebElement
	 * @param locator
	 * @return String
	 */
	public String doGetText(By locator) {
		
		return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator) {
		
		return getElement(locator).isDisplayed();
	}
	
	
	/**
	 * this is used to click on given link 
	 * @param linkList
	 * @param value
	 */
	public void doLinkClick(List<WebElement> linkList, String value) {
		
	System.out.println(linkList.size());
		
		for (WebElement ele : linkList) {
			
			System.out.println(ele.getText());
			
			if (ele.getText().equals(value)) {
				
				ele.click();
				break;
			}
			
		}
		
	}
	
	
	//------------------------------DropDown utils - Select class Methods************************
	
	/**
	 * this method is used to select value from drop down by passing text
	 * @param locator
	 * @param value
	 */
	public void selectDropDownValueByVisibleText(By locator, String value) {
		
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}
	
	/**
	 * this method is used to select value from drop down by given index
	 * @param locator
	 * @param index
	 */
	public void selectDropDownValueByIndex(By locator, int index) {
		
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	/**
	 * this method is used to select value from drop down by passing value
	 * @param locator
	 * @param value
	 */
	public void selectDropDownValueByValue(By locator, String value) {
	
	Select select = new Select(getElement(locator));
	select.selectByValue(value);
}
	
	/**
	 * this method is used to get options count from drop down
	 * @param locator
	 * @return options count 
	 */
	public int getDropdownOptionsCount(By locator) {
		
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		return optionsList.size();
		
	}
	
	/**
	 * this method is used to get options from the drop down
	 * @param locator
	 * @return List of options
	 */
	public List<String> getDropdownOptionsValue(By locator) {
		//top cast: List is parent interface for ArrayList class. 
		List<String> textList= new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		//optionsList.stream().forEach(ele -> System.out.println(ele.getText()));
		
		for (WebElement ele : optionsList) {
			
			String value = ele.getText();
			textList.add(value);
		}
		return textList;
		
	}
	
	// ***********************Select values from drop down without Select Class********************************
	
	/**
	 * this method is used to select value from drop down with out using Select class
	 * @param locator
	 * @param value
	 */
	public void selectValueFromDropdown(By locator, String value) {
		
		List<WebElement> countryList = driver.findElements(locator);
		
		for (WebElement ele : countryList) {
			
			String text = ele.getText();
			//System.out.println(text);
			
			if(text.equals(value)) {
				
				ele.click();
				break;
			}
		}
		
		
	}
	
	
	//*********************Wait Utils****************************
	
	public List<WebElement> visibilityOfAllElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void getPageLinksText(By locator, int timeOut) {
		visibilityOfAllElements(locator,timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
		
	}

	
	/**
	 * 
	 * @param locator
	 * @param timeOutSecs
	 * @return
	 */
	public WebElement waitForElementPresent(By locator,int timeOutSecs) {
		
		WebDriverWait wait = new WebDriverWait(driver, timeOutSecs);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	public WebElement waitForElementToBeVisible(By locator,int timeOutSecs) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOutSecs);
		return wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	
	public String waitForTitle(String title, int timeOutSecs) {
		
		WebDriverWait wait = new WebDriverWait(driver, timeOutSecs);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	public  boolean WaitForURL(String url, int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	public Alert waitForAlertToBePresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public WebElement waitForElementToBeClickable(By locator,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element =wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	public WebElement waitForElementWithFulentWait(By locator, int timeOut, int interval) {
		
		Wait<WebDriver> wait =new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(interval))
				.ignoring(NoSuchElementException.class); 
				// we can send multiple exceptions by cama separator
				//.ignoring(NoSuchElementException.class, NullPointerException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	//**********************************BrowserWindow Handle Util****************************************
	
	public Iterator<String> getWindowHandle() {
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		return iterator;
	}
	public String switchToChildBrowserWindow(String childWindId) {
		driver.switchTo().window(childWindId);
		String title= driver.getTitle();
		driver.close();
		return title;		
	}
	public String switchToParentBrowserWindow(String parentWindId) {
		driver.switchTo().window(parentWindId); //driver come back to parent window
		return driver.getTitle();
				
	}
	
//****************************** Miscellaneous utils*********************************************************
	
	public void getPageRefresh() {
		driver.navigate().refresh();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
