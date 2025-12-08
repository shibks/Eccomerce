package OrangeHrmPage;

import java.beans.JavaBean;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.WaitUtility;

public class LeavePage {
	
	WebDriver driver;

	public LeavePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	@FindBy(xpath="//span[normalize-space()='Leave']")
	WebElement leave;
	@FindBy(xpath="//a[normalize-space()='My Leave']")
	WebElement myleave;
	@FindBy(xpath="(//div[@class='oxd-date-input']//input)[1]")
	WebElement fromDateInput;
	@FindBy(xpath="//li[contains(@class,'oxd-calendar-selector-month')]/div")
	WebElement Month;
	@FindBy(xpath="//li[contains(@class,'oxd-calendar-selector-year')]/div")
	WebElement Year;
	@FindBy(xpath="(//div[@class='oxd-date-input']//input)[2]")
	WebElement toDateInput;
	@FindBy(xpath="(//div[@class='oxd-select-text-input'])[1]")
	WebElement leaveStatus;
	By selectedOptions =By.xpath("//div[@class='oxd-multiselect-chips-area']//span[contains(@class,'oxd-multiselect-chips-selected')]");
	
	String drpdownXpath="//div[@role='option']//span[text()='%s']";
	String extraDropdon="//span[contains(@class,'oxd-multiselect-chips-selected') and normalize-space()='%s']/i";
	
	public void selectFromDate(String year,String month,String day) {
		WaitUtility.waitforElementClickable(driver, fromDateInput).click();
		WaitUtility.waitforElementClickable(driver, Month).click();
		WebElement montH=driver.findElement(By.xpath("//li[normalize-space()='"+month+"']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",montH);
		WaitUtility.waitforElementClickable(driver, montH).click();
		
		WaitUtility.waitforElementClickable(driver, Year).click();
		WebElement yeaR=driver.findElement(By.xpath("//li[normalize-space()='"+year+"']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",yeaR);
		WaitUtility.waitforElementClickable(driver, yeaR).click();
		
		WebElement daY=	driver.findElement(By.xpath("//div[contains(text(),'"+day+"')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",daY);
		WaitUtility.waitforElementClickable(driver, daY).click();
		
		
		
	}
	
	public void selectToDate(String year,String month,String day) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", toDateInput);
		js.executeScript("arguments[0].click();", toDateInput);

		WaitUtility.waitforElementClickable(driver, Month).click();
		WebElement montH=driver.findElement(By.xpath("//ul[contains(@class,'oxd-calendar-dropdown')]//li[normalize-space()='"+month+"']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",montH);
		WaitUtility.waitforElementClickable(driver, montH).click();
		
		WaitUtility.waitforElementClickable(driver, Year).click();
		WebElement yeaR=driver.findElement(By.xpath("//ul[contains(@class,'oxd-calendar-dropdown')]//li[normalize-space()='"+year+"']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",yeaR);
		WaitUtility.waitforElementClickable(driver, yeaR).click();
		
		
		WebElement daY=	driver.findElement(By.xpath("//div[contains(text(),'"+day+"')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",daY);
		WaitUtility.waitforElementClickable(driver, daY).click();
		
	}
	
	public void selectLeaveStatus(String status) { 
		
		List<WebElement> chips=driver.findElements(selectedOptions);
		List<String> selected=new ArrayList<>();
		for(WebElement chip:chips) {
			selected.add(chip.getText());
		}
		
		System.out.println("-----selected----------> "+selected );
		
		List<String> expected=new ArrayList<String>();
		expected.add(status);
		
		System.out.println("-----Expected----->  "+expected);
		if(selected.isEmpty()) {
			WaitUtility.waitforElementVisible(driver, leaveStatus).click();
			for(String need:expected) {
				WebElement needDrpdown=driver.findElement(By.xpath(String.format(drpdownXpath,need)));
				WaitUtility.waitforElementClickable(driver, needDrpdown).click();
			}
			return;
		}
		
		List<String> missing=new ArrayList<String>();
		List<String> extra=new ArrayList<String>();
		
		for(String need:expected) {
			if(!selected.contains(need)) {
				missing.add(need);
			}
		}
		
		for(String sel:selected) {
			if(!expected.contains(sel)) {
				extra.add(sel);
			}
		}
		for(String ex:extra) {
			WebElement ele=driver.findElement(By.xpath(String.format(extraDropdon,ex )));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", ele);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);

		}
		
		
		
		
		if(!missing.isEmpty()) {
			WaitUtility.waitforElementVisible(driver, leaveStatus).click();
			for(String miss:missing) {
				WebElement missDrpdown=driver.findElement(By.xpath(String.format(drpdownXpath, miss)));
				WaitUtility.waitforElementClickable(driver, missDrpdown).click();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public void clickLeave() {
		WaitUtility.waitforElementVisible(driver, leave).click();
	}
	public void clickMyLeave() {
		WaitUtility.waitforElementVisible(driver, myleave).click();
	}
	
	
	
	

}
