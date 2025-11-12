package PageNew;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utilities.WaitUtility;

public class LoginPage1 {

	WebDriver driver;

	public LoginPage1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="div[class='login-form'] h2")
	WebElement loginHeader;
	@FindBy(css="input[data-qa='login-email']")
	WebElement email;
	@FindBy(name="password")
	WebElement password;
	@FindBy(css="button[data-qa='login-button']")
	WebElement login;
	@FindBy(xpath="//li[10]//a[1]")
	WebElement loginAsUsername;
	@FindBy(css="a[href='/delete_account']")
	WebElement deleteAcc; 
	@FindBy(css="h2[class='title text-center'] b")
	WebElement accDeleted;
	
	
	public void loginToAccVisible() {
		Assert.assertTrue(WaitUtility.waitforElementVisible(driver, loginHeader).isDisplayed(),"-----Not Login To Account Visible");
	}
	
	public void emailAndPassword() {
		WaitUtility.waitforElementVisible(driver, email).sendKeys("shibink3517@gmail.com");
		WaitUtility.waitforElementVisible(driver, password).sendKeys("123456");
		WaitUtility.waitforElementClickable(driver, login).click();
	}
	
	public void loggedInAsUserName() {
		String txt=WaitUtility.waitforElementVisible(driver, loginAsUsername).getText();
		Assert.assertTrue(txt.contains("Shibin"),"----userName Shibin Not found---------");
		System.out.println("----Text is :"+txt+" --------------");
	}
	
	public void deleteAccount() {
		WaitUtility.waitforElementClickable(driver, deleteAcc).click();
		Assert.assertTrue(WaitUtility.waitforElementVisible(driver, accDeleted).isDisplayed(),"---Account Deleted Not Displayed---");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
