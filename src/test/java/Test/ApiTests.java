package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import AssertionHelper.Assertionhelper;

import static io.restassured.RestAssured.given;

import base.BaseAPITest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiTests extends BaseAPITest {
	
	
	@Test(priority = 1)
	public void verifyPostMethodNotAllowed() {
		Response res=api.post("/productsList");
		res.then().log().all();
		Assert.assertEquals(res.jsonPath().getInt("responseCode"), 405);
		Assertionhelper.verifyMessage(res, "This request method is not supported.");
	}
	
	@Test(priority = 2)
	public void brandListApi() { 
		Response res=api.get("/brandsList");
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 200);
		int size=res.jsonPath().getList("brands").size();
		String brand=res.jsonPath().getString("brands[0].brand");
		Assert.assertTrue(size>0,"---Brand List Should not be empty");
		Assert.assertNotNull(brand, "-----Should not  be Null--------");
		System.out.println("Total Number of brands-----> "+size);
		
		
	}
}
