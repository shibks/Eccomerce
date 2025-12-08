package NopCommPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utilities.WaitUtility;
import base.BasePage;

public class ModelPopupPage extends BasePage {

	@FindBy(xpath="//button[@aria-label='Show SweetAlert2 success message']")
	WebElement showPopup;
	@FindBy(xpath="//button[normalize-space()='OK']")
	WebElement popup;
	
	public void clickLogin() {
		WaitUtility.waitforElementClickable(driver, showPopup).click();
	}
	
	public void closePopup() {
		try {
			WaitUtility.waitforElementClickable(driver, popup).click();
			System.out.println("Pop up appears and closed");
		}catch(Exception e) {
			System.out.println("Continuing--------");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
