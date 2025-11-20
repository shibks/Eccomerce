package PageNew;

import java.beans.JavaBean;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.WaitUtility;

public class ProductsPage1 {

	WebDriver driver;

	public ProductsPage1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	@FindBy(css=".title.text-center")
	WebElement allProductsPage;
	@FindBy(xpath="//a[contains(text(),'View Product')]")
	List<WebElement> products;
	@FindBy(xpath="(//a[contains(text(),'View Product')])[1]")
	WebElement firstProduct;
	@FindBy(xpath="//h2[normalize-space()='Blue Top']")
	WebElement productName;
	@FindBy(xpath="//p[normalize-space()='Category: Women > Tops']")
	WebElement category;
	@FindBy(xpath="//div[@class='product-details']//p[2]")
	WebElement availability;
	@FindBy(xpath="//a[normalize-space()='Home']")
	WebElement home;
	@FindBy(xpath="//input[@id='search_product']")
	WebElement search;
	@FindBy(css=".title.text-center")
	WebElement searchProductHeading;
	@FindBy(xpath="//a[contains(text(),'View Product')]")
	List<WebElement> visibleProducts;
	@FindBy(xpath="(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[1]")
	WebElement addToCart1;
	@FindBy(xpath="(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[3]")
	WebElement addToCart2;
	@FindBy(xpath="//a[normalize-space()='Cart']")
	WebElement cart;
	@FindBy(xpath="//button[@class='btn btn-success close-modal btn-block']")
	WebElement continueShopping;
	
	public void allProductsVisible() {
		Assert.assertTrue(WaitUtility.waitforElementVisible(driver, allProductsPage).isDisplayed(),"---No Not displayed--");
	}
	
	public void productListIsVisible() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(products));
		boolean size= products.size() >0;
		Assert.assertTrue(size,"-----products not Displayed------");
	}
	
	public void firstProduct() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",firstProduct);
		WaitUtility.waitforElementClickable(driver, firstProduct).click();
	}
	
	public void productDetails() {
		boolean pro= WaitUtility.waitforElementVisible(driver, productName).isDisplayed() &&
		WaitUtility.waitforElementVisible(driver, category).isDisplayed() && 
		WaitUtility.waitforElementVisible(driver, availability).isDisplayed();
		
		Assert.assertTrue(pro,"----product name , price , availability all visible-------");
	}
	public void goHome() {
		WaitUtility.waitforElementClickable(driver, home).click();
		HomePage1 homePage1=new HomePage1(driver);
		homePage1.isHomeVisible();
	}
	
	public void productSearch() {
		WaitUtility.waitforElementVisible(driver, search).sendKeys("shirt");
	}
	
	public void searchProductIsVisible() {
		Assert.assertTrue(WaitUtility.waitforElementVisible(driver, searchProductHeading).isDisplayed(),"----No Not Displayed");
	}
	
	public void searchAllProductsVisible() {
		Assert.assertTrue(visibleProducts.size()>0);
	}
	
	public void addFirstProductToCart() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",addToCart1);
		addToCart1.click();
	}
	public void clickContinueShopping() {
		WaitUtility.waitforElementClickable(driver, continueShopping).click();
	}

	public void addSecondProductToCart() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",addToCart2);
		addToCart2.click();
		clickContinueShopping();
	}
	public void clickCart() {
		WaitUtility.waitforElementClickable(driver, cart).click();
	}
	
	
	
	
	
	
	
	
	
	
	
}