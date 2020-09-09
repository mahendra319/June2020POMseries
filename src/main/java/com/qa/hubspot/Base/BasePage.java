package com.qa.hubspot.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author Mahi
 *
 */
public class BasePage {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String flashElement;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	/**
	 * this method is used to initialise WebDriver on basis of given browser name
	 * @param prop
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {
		
		
		flashElement = prop.getProperty("highlight");
		
		String browserName = null;
		if(System.getProperty("browser")==null) {
			browserName = prop.getProperty("browser").trim();
		}else {
			browserName = System.getProperty("browser");
		}
				
		System.out.println("Browser Name is: "+browserName);
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOpotions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOpotions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOpotions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOpotions()));
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		else {
			System.out.println("Please pass correct brwoser name: "+browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("url"));
		
		
		return getDriver();
		
	}
	
	/**
	 * getDriver using ThreadLocal
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/**
	 * this method is used to get the properties values from config file
	 * @return prop
	 */
	public Properties init_prop() {
		
		prop = new Properties();
		String path = null;
		String env = null;//env variable passing through command prompt
		
		try {
			
			
			env =System.getProperty("env");
			System.out.println("Running on Environment: "+env);
			if(env==null) {
				System.out.println("Running on Environment: "+" PROD");
				path = "./src/main/java/com/qa/hubspot/config/config.properties";
				
			}
			
			else {
				switch (env){
				case "qa":
					path = "./src/main/java/com/qa/hubspot/config/config.qa.properties";
					break;
				case "dev":
					path="./src/main/java/com/qa/hubspot/config/config.dev.properties";
					break;
				case "stage":
					path = "./src/main/java/com/qa/hubspot/config/config.stage.properties";
					break;
				default:
					System.out.println("Please pass the correct env value....");
					break;
				}
			}
					
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * This method is used to take screenshot 
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screensthots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
