package Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import AssertionHelper.Assertionhelper;
import Utilities.UserDataAPIUtil;

import static io.restassured.RestAssured.given;

import java.util.Map;

import base.BaseAPITest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiTests extends BaseAPITest {
	
	
	@Test(priority = 1)
	public void verifyPostMethodNotAllowed() {
		Response res=api.post("/productsList");
		Reporter.getCurrentTestResult().getTestContext().setAttribute("API_RESPONSE", res);
		Assert.assertEquals(res.jsonPath().getInt("responseCode"), 405);
		Assertionhelper.verifyMessage(res, "This request method is not supported.");
	}
	
	@Test(priority = 2)
	public void brandListApi() { 
		Response res=api.get("/brandsList");
		Reporter.getCurrentTestResult().getTestContext().setAttribute("API_RESPONSE", res);
		Assert.assertEquals(res.statusCode(), 200);
		int size=res.jsonPath().getList("brands").size();
		String brand=res.jsonPath().getString("brands[0].brand");
		Assert.assertTrue(size>0,"---Brand List Should not be empty");
		Assert.assertNotNull(brand, "-----Should not  be Null--------");
		System.out.println("Total Number of brands-----> "+size);
	}
	@Test(priority = 4)
	public void verifyLoginWithValidDetails() {
		Response res=api.verifyLogin("shibink3517@gmail.com", "123456");
		Reporter.getCurrentTestResult().getTestContext().setAttribute("API_RESPONSE", res);
		Assert.assertEquals(res.jsonPath().getString("message"), "User exists!","Status verification failed");
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void registerAccount() {
		Map<String, String> userData = UserDataAPIUtil.getCreateAccountData();
		Response res=api.createAccount(userData);
		Reporter.getCurrentTestResult().getTestContext().setAttribute("API_RESPONSE", res);
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("message")," User created!","----User Is NOT CREATED_____");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
