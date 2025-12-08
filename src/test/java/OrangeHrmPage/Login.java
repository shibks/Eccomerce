package OrangeHrmPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utilities.WaitUtility;

public class Login {
	
	WebDriver driver;

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}

	@FindBy(css="input[placeholder='Username']")
	WebElement user;
	@FindBy(css="input[placeholder='Password']")
	WebElement password;
	@FindBy(css="button[type='submit']")
	WebElement submit;
	@FindBy(xpath="//img[@alt='client brand banner']")
	WebElement svgIcon;
	
	public void enterUser() {
		WaitUtility.waitforElementVisible(driver, user).sendKeys("Admin");
	}
	
	public void enterPass() {
		WaitUtility.waitforElementVisible(driver, password).sendKeys("admin123");
	}
	public void submit() {
		WaitUtility.waitforElementClickable(driver, submit).click();
	}
	public void isAdminPanelVisible() {
		boolean isPresent=WaitUtility.elementIsPresent(driver, svgIcon, 3);
		Assert.assertTrue(isPresent, "----No Admin Page Not Visible");
	}
}
