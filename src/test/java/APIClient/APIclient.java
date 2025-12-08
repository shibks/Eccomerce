package APIClient;
import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIclient {

	public Response post(String endpoint) {
		return given()
		.when()
		.post(endpoint)
		.then()
		.extract().response();
	}
	
	public Response get(String endpoint) {
		return given()
				
				.when()
				.get(endpoint)
				.then()
				
				.extract().response();
	}
	
	public Response verifyLogin(String username,String password) {
		return given()
				.contentType(ContentType.URLENC)
				.formParam("email",username)
				.formParam("password", password)
			
				.when()
				.post("verifyLogin")
				.then().extract().response();
	}
	
	public Response createAccount(Map<String, String> userData) {
		return given()
				.contentType(ContentType.URLENC)
				.formParams(userData)
				.when()
				.post("/createAccount")
				.then().extract().response();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
