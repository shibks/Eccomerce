package Test;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import Page.AddressPage;
import Page.Books;
import Page.LoginPage;
import Page.RegisterPage;
import Utilities.WaitUtility;
import base.BaseTest;


public class SmokeTest extends BaseTest{
	


	
	public void userRegistration() throws InterruptedException {
		RegisterPage page=new RegisterPage(driver);
		page.clickRegister();
		page.clickGender();
		page.firstName();
		page.lastName();
		page.email();
		page.password();
		page.confirmPassword();
		page.register();
	    page.clickContin();
	   
	}
	

	public void userBooks() throws InterruptedException {
		Books book=new Books(driver);
		book.clickBook();
		book.sortDropDown();
		book.selectFirstBook();
		book.addToCart();
		WaitUtility.waitForSeconds(driver, 5);
		book.cartView();
		book.clickLogout();
		Assert.assertTrue(book.login(),"Element not visible");
		
	}
	
	@Test(priority =1)
	public void login() {
		LoginPage loginpg=new LoginPage(driver);
		loginpg.clickLogin();
		loginpg.enterEmail();
		loginpg.enterPass();
		loginpg.clickLoginBtn();
		loginpg.loginEmailcheck();
		
	}
	@Test(priority=2)
	public void address() {
		AddressPage addpage=new AddressPage(driver);
		addpage.addressClick();
		addpage.addNew();
		addpage.addAddress();
		addpage.deleteAddress(driver);
		//addpage.noAddress();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
