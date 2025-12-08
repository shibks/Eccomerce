package Utilities;

import java.io.File;
import java.io.FilenameFilter;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	 private static final int DEFAULT_WAIT = 30;
	
	public static WebElement waitforElementVisible(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(DEFAULT_WAIT));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static WebElement waitforElementClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(DEFAULT_WAIT));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static List<WebElement> waitForElementsToBeVisible(WebDriver driver,  List<WebElement> element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(DEFAULT_WAIT));
		return wait.until(ExpectedConditions.visibilityOfAllElements(element));
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
	public static File waitForFile(String dirPath, String extension, int timeoutSeconds) {

	    File dir = new File(dirPath);

	    if (!dir.exists() || !dir.isDirectory()) {
	        System.out.println("Directory does not exist: " + dirPath);
	        return null;
	    }

	    int elapsedSeconds = 0;

	    while (elapsedSeconds < timeoutSeconds) {

	        File[] files = dir.listFiles(new FilenameFilter() {
	            @Override
	            public boolean accept(File d, String name) {
	                return name.toLowerCase().endsWith(extension.toLowerCase());
	            }
	        });

	        if (files != null && files.length > 0) {
	            return files[0];   // return the first matching file
	        }

	        try {
	            Thread.sleep(1000);  // wait for 1 second
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        elapsedSeconds++;
	    }

	    return null; // no file found within timeout
	}

}
