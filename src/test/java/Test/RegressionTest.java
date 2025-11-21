package Test;

import org.testng.annotations.Test;

import PageNew.CartPage;
import PageNew.ContactUsPage1;
import PageNew.HomePage1;
import PageNew.LoginPage1;
import PageNew.ProductsPage1;
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

	@Test(priority = 2)
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
	
	@Test(priority = 3)
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
	
	@Test(priority=4)
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
	
	@Test(priority = 5)
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
	
	@Test(priority = 6)
	public void categoryCheck() {
		HomePage1 home=new HomePage1(driver);
		ProductsPage1 product=new ProductsPage1(driver);
		home.isHomeVisible();
		product.clickWomenCategory();
		product.checkWomenCategoryHeading();
		product.clickMenCategory();
		product.checkMensCategoryHeading();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
