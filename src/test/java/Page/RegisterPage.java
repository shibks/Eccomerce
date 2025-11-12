package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.WaitUtility;
import Utilities.randomEmail;

public class RegisterPage {
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".ico-register")
	WebElement registerlnk;
	@FindBy(css="#gender-male")
	WebElement genderBtn;
	@FindBy(css="#FirstName")
	WebElement firstName;
	@FindBy(css="#LastName")
	WebElement lastName;
	@FindBy(css="#Email")
	WebElement emailID;
	@FindBy(css="#Password")
	WebElement password;
	@FindBy(css="#ConfirmPassword")
	WebElement confirmPass;
	@FindBy(css="#register-button")
	WebElement register;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement contin;
	@FindBy(css=".ico-logout")
	WebElement logout;
	
	public void clickRegister() {
		WaitUtility.waitforElementClickable(driver, registerlnk).click();
	}
	
	public void clickGender() {
		WaitUtility.waitforElementClickable(driver, genderBtn).click();	
		}
	
	public void firstName() {
		WaitUtility.waitforElementVisible(driver, firstName).sendKeys("Chris");
	}
	
	public void lastName() {
		WaitUtility.waitforElementVisible(driver, lastName).sendKeys("Hermsworth");
	}
	
	public void email() {
		String email=randomEmail.generateRandomEmail();
		WaitUtility.waitforElementVisible(driver, emailID).sendKeys(email);
	}
	
	public void password() {
		WaitUtility.waitforElementVisible(driver, password).sendKeys("123456");
	}
	
	public void confirmPassword() {
		WaitUtility.waitforElementVisible(driver, confirmPass).sendKeys("123456");
	}
	
	public void register() {
		WaitUtility.waitforElementVisible(driver, register).click();
	}
	
	
	 public void clickContin() {
		 WaitUtility.waitforElementClickable(driver, contin).click();
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
