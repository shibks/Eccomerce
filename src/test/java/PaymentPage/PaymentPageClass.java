package PaymentPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Utilities.WaitUtility;

public class PaymentPageClass {


	WebDriver driver;

	public PaymentPageClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	@FindBy(css="input[placeholder='Jenny Rosen']")
	WebElement name;
	@FindBy(css="input[placeholder='jenny@example.com']")
	WebElement email;
	@FindBy(css="input[placeholder='185 Berry Street Suite 550']")
	WebElement address;
	@FindBy(css="input[placeholder='San Francisco']")
	WebElement city;
	@FindBy(css="input[placeholder='CA']")
	WebElement state;
	@FindBy(css="input[placeholder='94103']")
	WebElement zip;
	@FindBy(css="select[name='country']")
	WebElement country;
	@FindBy(css="iframe[title='Secure card payment input frame']")
	WebElement iframe;
	@FindBy(css="input[placeholder='Card number']")
	WebElement cardNo;
	@FindBy(css="input[placeholder='MM / YY']")
	WebElement expiry;
	@FindBy(xpath="//button[contains(text(),'Pay')]")
	WebElement payButton;
	@FindBy(xpath="//p[normalize-space()='Woot! You successfully made a payment with Stripe.']")
	WebElement successMsg;
	@FindBy(css="input[placeholder='CVC']")
	WebElement cvs;
	@FindBy(css=".error-message")
	WebElement failure;
	
	public void enterShipingInfo() {
		WaitUtility.waitforElementVisible(driver, name).clear();
		name.sendKeys("shibi");
		WaitUtility.waitforElementVisible(driver, email).clear();
		email.sendKeys("email@gmail.com");
		WaitUtility.waitforElementVisible(driver, address).clear();
		address.sendKeys("try at the near ");
		WaitUtility.waitforElementVisible(driver, city).clear();
		city.sendKeys("kuruchi");
		WaitUtility.waitforElementVisible(driver, state).clear();
		state.sendKeys("LA");
		WaitUtility.waitforElementVisible(driver, zip).clear();
		zip.sendKeys("12345");
		WaitUtility.waitforElementVisible(driver, country);
		Select sel=new Select(country);
		sel.selectByVisibleText("Spain");
	}
	
	private void switchToIframe(WebElement element) {
		driver.switchTo().frame(element);	
		}
	
	private void switchToDefault() {
		driver.switchTo().defaultContent();
	}
	public void enterCardDetails(String carddNo,String expiryDate,String cvv) {
		switchToIframe(iframe);
		WaitUtility.waitforElementVisible(driver, cardNo).sendKeys(carddNo);
		WaitUtility.waitforElementVisible(driver, expiry).sendKeys(expiryDate);
		WaitUtility.waitforElementVisible(driver, cvs).sendKeys(cvv);
		switchToDefault();
		WaitUtility.waitforElementClickable(driver, payButton).click();
		
	}
	public void checkSuccessMsg() {
		String msg=WaitUtility.waitforElementVisible(driver, successMsg).getText();
		Assert.assertEquals(msg, "Woot! You successfully made a payment with Stripe.");
	}
	
	public void checkFailureMsg() {
		String msg=WaitUtility.waitforElementVisible(driver, failure).getText();
		Assert.assertEquals(msg, "Your card has insufficient funds. Try a different card.");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
