package OrangeHrmPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utilities.WaitUtility;
import base.BasePage;

public class CalenderPage extends BasePage {
	@FindBy(xpath="//span[contains(text(),'examples')]")
	WebElement example;
	@FindBy(xpath="(//span[@class='mat-mdc-button-touch-target'])[17]")
	WebElement fromIcon;
	@FindBy(xpath="(//span[@class='mat-mdc-button-touch-target'])[18]")
	WebElement toIcon;
	@FindBy(xpath="//mat-calendar[@id='mat-datepicker-24']//span[@class='mdc-button__label']")
	WebElement yearGrid;
	
	
	public void selectfromDate(String targetYear,String targetMonth,String targetDay ) {
		WaitUtility.waitforElementClickable(driver, fromIcon).click();
		WaitUtility.waitforElementClickable(driver, yearGrid).click();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
