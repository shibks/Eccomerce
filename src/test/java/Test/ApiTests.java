package Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import base.BaseAPITest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiTests extends BaseAPITest {
	
	
	@Test
	public void getAllProductLists() {
		
		Response response=given()
		.when()
		.get("/productsList")
		.then()
		.extract().response();
		JsonPath json=response.jsonPath();
		int statuscode=response.getStatusCode();
		
		Assert.assertEquals(statuscode, 200,"Expected status code is 200");
		
		String productName=json.getString("products[0].name");
		String genderType=json.getString("products[0].category.usertype.usertype");
		
		Assert.assertEquals(productName, "Blue Top");
		Assert.assertEquals(genderType, "Women");
		Assert.assertEquals(json.getString("products[0].category.category"), "Tops");
	}

}
