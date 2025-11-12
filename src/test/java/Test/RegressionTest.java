package Test;

import org.testng.annotations.Test;

import PageNew.ContactUsPage1;
import PageNew.HomePage1;
import PageNew.LoginPage1;
import PageNew.ProductsPage1;
import base.BaseTest;

public class RegressionTest extends BaseTest {
	
	
	public void LoginWithCorrectUserAndEmail()
	{
		HomePage1 home=new HomePage1(driver);
		LoginPage1 login=new LoginPage1(driver);
		home.isHomeVisible();
		home.clickLogin();
		login.loginToAccVisible();
		login.emailAndPassword();
		login.loggedInAsUserName();
		login.deleteAccount();
	}

	@Test(priority = 1)
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
	@Test(priority = 2)
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
