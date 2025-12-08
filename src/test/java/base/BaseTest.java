package base;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import Utilities.ConfigReader;
import Utilities.EnvConfig;


public class BaseTest {
public WebDriver driver;
	

	@BeforeClass
	public void setup(ITestContext context) {
		
		driver=DriverFactory.initDriver();
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();

		 context.setAttribute("WebDriver", driver);
System.out.println("------Driver Initialized---------");

driver.get(EnvConfig.getURL());


	}
	
@AfterClass
	public void teardown() {
		DriverFactory.quitDriver();
	}

}
