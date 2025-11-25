package base;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utilities.ConfigReader;


public class DriverFactory {
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	
	public static WebDriver initDriver() {
		ConfigReader.loadProperties();
		String browser = System.getProperty("browser");
		System.out.println(">>>------------ Received browser from Jenkins: " + System.getProperty("browser"));
		System.out.println(">>>-------------- Browser after fallback logic: " + browser);

		if (browser == null || browser.isEmpty()) {
		  
		    browser = ConfigReader.getProperty("browser");
		}

		browser = browser.toLowerCase();
		switch(browser) {
		case "chrome":
			
			driver.set(new ChromeDriver());
			break;
			
		case "firefox" :
			
			driver.set(new FirefoxDriver());
			break;
			
		case "edge":
		
			System.setProperty("webdriver.edge.driver", "C:\\Automation\\Selenium\\Drivers\\msedgedriver.exe");
			driver.set(new EdgeDriver());
			break;
			
			default:
                throw new IllegalArgumentException("❌ Invalid browser specified in config.properties: " + browser);
		}
		
	return driver.get();
		
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void quitDriver(){
		if(driver.get() !=null) {
			getDriver().quit();
			driver.remove();
		}
		
	}

}
