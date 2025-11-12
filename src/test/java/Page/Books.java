package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utilities.WaitUtility;

public class Books {
	WebDriver driver;
	public Books(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//li[@class='inactive']//a[normalize-space()='Books']")
	WebElement books;
	@FindBy(css="#products-orderby")
	WebElement sortDropdown;
	@FindBy(css="#products-pagesize")
	WebElement sortPagesize;
	@FindBy(xpath="(//div[@class='item-box'])[1]")
	WebElement firstBook;
	@FindBy(xpath="(//input[@id='add-to-cart-button-13'])[1]")
	WebElement addToCart;
	@FindBy(xpath="//span[normalize-space()='Shopping cart']")
	WebElement cartDisplay;
	@FindBy(xpath="(//a[@class='product-name'])[1]")
	WebElement product;
	@FindBy(css=".ico-logout")
	WebElement logout;
	@FindBy(css=".ico-login")
	WebElement login;
	
	public void clickBook() {
		WaitUtility.waitforElementClickable(driver, books).click();
	}
	
	public void sortDropDown() {
		Select select=new Select(sortDropdown);
		select.selectByVisibleText("Price: Low to High");
		
		Select sel=new Select(sortPagesize);
		sel.selectByVisibleText("8");
	}
	
	public void selectFirstBook() {
		WaitUtility.waitforElementClickable(driver, firstBook).click();
	}
	
	public void addToCart() {
		WaitUtility.waitforElementClickable(driver, addToCart).click();
	}
	
	public void cartView() {
		WaitUtility.waitforElementClickable(driver, cartDisplay).click();
	}
	
	public String firstProduct() {
		return WaitUtility.waitforElementVisible(driver, product).getText();
	}
	
	public void clickLogout() {
		WaitUtility.waitforElementClickable(driver, logout).click();
	}
	
	public boolean login() {
		return WaitUtility.waitforElementVisible(driver,login).isDisplayed();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
