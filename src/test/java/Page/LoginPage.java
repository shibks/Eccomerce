package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utilities.WaitUtility;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".ico-login")
	WebElement loginBtn;
	@FindBy(css = "#Email")
	WebElement email;
	@FindBy(css = "#Password")
	WebElement password;
	@FindBy(css = "#RememberMe")
	WebElement rememberMe;
	@FindBy(xpath="//input[@value='Log in']")
	WebElement login;
	@FindBy(xpath="//a[normalize-space()='shibink3517@gmail.com']")
	WebElement loginEmail;
	
	public void getUrl() {
		
	}
	
	public void clickLogin() {
		WaitUtility.waitforElementClickable(driver, loginBtn).click();
	}
	
	public void enterEmail() {
		WaitUtility.waitforElementVisible(driver, email).sendKeys("shibink3517@gmail.com");
	}
	public void enterPass() {
		WaitUtility.waitforElementVisible(driver, password).sendKeys("123456");
		WaitUtility.waitforElementClickable(driver, rememberMe).click();
	}
	
	public void clickLoginBtn() {
		WaitUtility.waitforElementClickable(driver, login).click();
	}
	
	public void loginEmailcheck() {
		String text=WaitUtility.waitforElementVisible(driver, loginEmail).getText();
		Assert.assertEquals(text,"shibink3517@gmail.com","Tiles doesn't match");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
