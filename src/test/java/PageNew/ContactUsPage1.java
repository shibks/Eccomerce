package PageNew;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.WaitUtility;

public class ContactUsPage1 {
	WebDriver driver;

	public ContactUsPage1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(css = "input[placeholder='Name']")
	WebElement name;
	@FindBy(css = "input[placeholder='Email']")
	WebElement email;
	@FindBy(css = "input[placeholder='Subject']")
	WebElement subject;
	@FindBy(css = "#message")
	WebElement message;
	@FindBy(css = "input[name='upload_file']")
	WebElement upload;
	@FindBy(css = "input[value='Submit']")
	WebElement submit;
	@FindBy(css = ".status.alert.alert-success")
	WebElement successMsg;
	@FindBy(css = ".btn.btn-success")
	WebElement home;

	public void enterDetails() {
		WaitUtility.waitforElementVisible(driver, name).sendKeys("Shibin K S");
		WaitUtility.waitforElementVisible(driver, email).sendKeys("shibink3417@gmail.com");
		WaitUtility.waitforElementVisible(driver, subject).sendKeys("Notthing ");
		WaitUtility.waitforElementVisible(driver, message).sendKeys("Hello Dude");
		
	}
	
	public void uploadFile() {
		WaitUtility.waitforElementVisible(driver, upload).sendKeys("C:\\Users\\SHIBIN\\Downloads\\Lucifer.S06E01.720p.NF.WEBRip.x264-GalaxyTV.srt");
		WaitUtility.waitforElementClickable(driver, submit).click();
		try{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert=driver.switchTo().alert();
			System.out.println("-----Alert text is : "+alert.getText()+"--------------");
			alert.accept();
		}catch(Exception e) {
			System.out.println("------No Alert Is Present-----------");
		}
	}
	
	public void successMsgIsDisplayed() {
		Assert.assertTrue(WaitUtility.waitforElementVisible(driver, successMsg).isDisplayed(),"--Success Message not Displayed--");
	}
	
	public void clickHome() {
		WaitUtility.waitforElementClickable(driver, home).click();
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
