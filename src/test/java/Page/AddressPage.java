package Page;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.WaitUtility;
import Utilities.randomEmail;

public class AddressPage {
	WebDriver driver;

	public AddressPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Addresses']")
	WebElement address;
	@FindBy(xpath = "//input[@value='Add new']")
	WebElement addnew;
	@FindBy(css = "#Address_FirstName")
	WebElement firstName;
	@FindBy(css = "#Address_LastName")
	WebElement lastName;
	@FindBy(css = "#Address_Email")
	WebElement emailID;
	@FindBy(xpath = "//select[@id='Address_CountryId']")
	WebElement countryDrpdown;
	@FindBy(css = "#Address_City")
	WebElement addCity;
	@FindBy(css = "#Address_Address1")
	WebElement addLine1;
	@FindBy(css = "#Address_ZipPostalCode")
	WebElement postalCode;
	@FindBy(css = "#Address_PhoneNumber")
	WebElement phoneNumber;
	@FindBy(xpath = "//input[@value='Save']")
	WebElement save;
	@FindBy(xpath = "//input[@value='Delete']")
	WebElement delete;
	@FindBy(css = ".address-list")
	WebElement noadressPresent;

	public void addressClick() {
		WaitUtility.waitforElementClickable(driver, address).click();
	}

	public void addNew() {
		WaitUtility.waitforElementClickable(driver, addnew).click();
	}

	public void addAddress() {
		WaitUtility.waitforElementVisible(driver, firstName).sendKeys("Shibi");
		WaitUtility.waitforElementVisible(driver, lastName).sendKeys("Hermworth");
		String email = randomEmail.generateRandomEmail();
		WaitUtility.waitforElementVisible(driver, emailID).sendKeys(email);
		Select sel = new Select(countryDrpdown);
		sel.selectByVisibleText("India");
		WaitUtility.waitforElementVisible(driver, addCity).sendKeys("Arumanai");
		WaitUtility.waitforElementVisible(driver, addLine1).sendKeys("Somewhere in Arumanai");
		WaitUtility.waitforElementVisible(driver, postalCode).sendKeys("629151");
		String num = randomEmail.generateRandomPhoneNumber();
		WaitUtility.waitforElementVisible(driver, phoneNumber).sendKeys(num);
		WaitUtility.waitforElementClickable(driver, save).click();
	}

	public void deleteAddress(WebDriver driver) {
		if (delete.isDisplayed() && delete.isEnabled()) {
			delete.click();
			System.out.println("-------Delete clicked--------");
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.alertIsPresent());

				Alert alert = driver.switchTo().alert();
				System.out.println("Alert Text: " + alert.getText());
				alert.accept();
				WaitUtility.waitForSeconds(driver, 10);
			} catch (Exception e) {
				System.out.println("No alert appeared after clicking delete.");
			}
		} else {
			System.out.println("Address not Created");
		}
	}
	
	public void noAddress() {
		Assert.assertTrue(noadressPresent.isDisplayed(), "Element not visible on the page");
	}

}
