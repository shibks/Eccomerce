package Test;

import org.testng.annotations.Test;

import PageNew.CartPage;
import PageNew.ContactUsPage1;
import PageNew.HomePage1;
import PageNew.LoginPage1;
import PageNew.ProductsPage1;
import PaymentPage.PaymentPageClass;
import Utilities.ConfigReader;
import base.BaseTest;

public class RegressionTest extends BaseTest {
	
	@Test(priority = 1)
	public void LoginWithCorrectUserAndEmail()
	{
		HomePage1 home=new HomePage1(driver);
		LoginPage1 login=new LoginPage1(driver);
		home.isHomeVisible();
		home.clickLogin();
		login.loginToAccVisible();
		login.emailAndPassword();
		login.loggedInAsUserName();
		
	}

	
	public void AllProductsAndProductDetailPage() {
		HomePage1 home=new HomePage1(driver);
		ProductsPage1 product=new ProductsPage1(driver);
		home.isHomeVisible();
		home.clickProductsPage();
		product.allProductsVisible();
		product.productListIsVisible();
		product.firstProduct();
		product.productDetails();
		product.goHome();
	}
	
	
	public void contactUsForm() {
		HomePage1 home=new HomePage1(driver);
		ContactUsPage1 contact=new ContactUsPage1(driver);
		home.isHomeVisible();
		home.clickContactUs();
		home.checkGetInTouchVisible();
		contact.enterDetails();
		contact.uploadFile();
		contact.successMsgIsDisplayed();
		contact.clickHome();
		home.isHomeVisible();
	}
	
	
	public void searchProduct() {
		HomePage1 home=new HomePage1(driver);
		ProductsPage1 page=new ProductsPage1(driver);
		home.isHomeVisible();
		home.clickProductsPage();
		page.allProductsVisible();
		page.productSearch();
		page.searchProductIsVisible();
		page.searchAllProductsVisible();
		page.goHome();
	}
	
	
	public void addProductsToCart() {
		HomePage1 home=new HomePage1(driver);
		ProductsPage1 page=new ProductsPage1(driver);
		CartPage cart=new CartPage(driver);
		home.isHomeVisible();
		home.clickProductsPage();
		page.allProductsVisible();
		page.addFirstProductToCart();
		page.clickContinueShopping();
		page.addSecondProductToCart();
		page.clickCart();
		cart.verifyProductNames();
		cart.verifyTotalPrice();
		page.goHome();
		
	}
	

	public void categoryCheck() {
		HomePage1 home=new HomePage1(driver);
		ProductsPage1 product=new ProductsPage1(driver);
		home.isHomeVisible();
		product.clickWomenCategory();
		product.checkWomenCategoryHeading();
		product.clickMenCategory();
		product.checkMensCategoryHeading();
		
	}
	
	
	public void paymentCheck() {
		driver.get(ConfigReader.getProperty("paymentUrl"));
		PaymentPageClass payment=new PaymentPageClass(driver);
		payment.enterShipingInfo();
		payment.enterCardDetails("4242 4242 4242 4242","1234","123");
		payment.checkSuccessMsg();
	}
	

	public void expiredPaymentCheck() {
		driver.get(ConfigReader.getProperty("paymentUrl"));
		PaymentPageClass payment=new PaymentPageClass(driver);
		payment.enterShipingInfo();
		payment.enterCardDetails("4000 0000 0000 9995","1234","123");
		payment.checkFailureMsg();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
