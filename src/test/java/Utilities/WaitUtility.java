package Utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	 private static final int DEFAULT_WAIT = 20;
	
	public static WebElement waitforElementVisible(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(DEFAULT_WAIT));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static WebElement waitforElementClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(DEFAULT_WAIT));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitForSeconds(WebDriver driver, int seconds) {
	    try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
	        wait.until(ExpectedConditions.jsReturnsValue("return true;"));
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static boolean elementIsPresent(WebDriver driver,WebElement element,int timeout) {
		try {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
}
