package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import APIClient.APIclient;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BaseAPITest {
	
	protected APIclient api;
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://automationexercise.com/api";
		RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter());
		 System.out.println("✅ Base URI Set: " + RestAssured.baseURI);
		 api=new APIclient();
	}

}
