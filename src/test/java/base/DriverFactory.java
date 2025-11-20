package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	
	public static WebDriver initDriver() {
		ConfigReader.loadProperties();
		String browser=ConfigReader.getProperty("browser").toLowerCase();
		
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			break;
			
		case "firefox" :
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			break;
			
		case "edge":
			WebDriverManager.edgedriver().setup();
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
