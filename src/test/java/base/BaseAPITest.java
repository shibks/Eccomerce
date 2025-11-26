package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import APIClient.APIclient;
import io.restassured.RestAssured;

public class BaseAPITest {
	
	protected APIclient api;
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://automationexercise.com/api";
		 System.out.println("✅ Base URI Set: " + RestAssured.baseURI);
		 api=new APIclient();
	}

}
