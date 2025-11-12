package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseAPITest {
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://automationexercise.com/api";
		 System.out.println("✅ Base URI Set: " + RestAssured.baseURI);
	}

}
