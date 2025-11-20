package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import Utilities.ConfigReader;


public class BaseTest {
public WebDriver driver;
	

	@BeforeClass
	public void setup(ITestContext context) {
		
		driver=DriverFactory.initDriver();
		driver.manage().window().maximize();
		 context.setAttribute("WebDriver", driver);
System.out.println("------Driver Initialized---------");
driver.get(ConfigReader.getProperty("url"));
	}
	
	@AfterClass
	public void teardown() {
		DriverFactory.quitDriver();
	}

}
