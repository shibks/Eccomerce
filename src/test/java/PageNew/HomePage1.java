package PageNew;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utilities.WaitUtility;

public class HomePage1 {

	WebDriver driver;

	public HomePage1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@id='slider-carousel']//div[@class='carousel-inner']")
	WebElement slider;
	@FindBy(css="a[href='/login']")
	WebElement login;
	@FindBy(css="a[href='/products']")
	WebElement products;
	@FindBy(css="a[href='/contact_us']")
	WebElement contactUs;
	@FindBy(xpath="div[class='contact-form'] h2[class='title text-center']")
	WebElement getInTouch;
	
	public void isHomeVisible() {
		boolean displayed=WaitUtility.waitforElementVisible(driver, slider).isDisplayed();
		Assert.assertTrue(displayed,"---Not Displayed the home slider-----");
	}
	
	public void clickProductsPage() {
		WaitUtility.waitforElementClickable(driver, products).click();
	}
	
	public void clickLogin() {
		WaitUtility.waitforElementClickable(driver, login).click();
	}
	
	public void clickContactUs() {
		WaitUtility.waitforElementClickable(driver, contactUs).click();
		
	}
	public void checkGetInTouchVisible() {
		Assert.assertTrue(WaitUtility.elementIsPresent(driver, contactUs, 20),"----Get In Touch Is Not Visible---------");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
