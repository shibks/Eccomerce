package PageNew;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utilities.WaitUtility;

public class CartPage {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	@FindBy(xpath="//tbody/tr/td[2]/h4/a")
	List<WebElement> products;
	@FindBy(xpath="//tbody/tr/td[5]/p")
	List<WebElement> prices;
	
	
	public int getCartItemCount() {
		return WaitUtility.waitForElementsToBeVisible(driver, products).size();
	}
	
	public void verifyProductNames() {
		List<String> names=new ArrayList<String>();
		for(WebElement product:products) {
			String name=product.getText().trim();
			names.add(name);
		}
		Assert.assertEquals(names.get(0),"Blue Top","--Product Name Is Missmatch--");
		Assert.assertEquals(names.get(1),"Men Tshirt","--Product Name Is Missmatch--");
	}
	
	public void verifyTotalPrice() {
		List<Double> pricess=new ArrayList<Double>();
		for(WebElement price:prices) {
			String amt=price.getText().trim();
			String cleanPrice = amt.replace("Rs.", "").replace(",", "").trim();
			double pricee = Double.parseDouble(cleanPrice);
			pricess.add(pricee);
		}
		Assert.assertEquals(pricess.get(0), 500,"------Total Price Mismatch-------------");
		Assert.assertEquals(pricess.get(1), 400,"-------Total Price Mismatch----------------");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
