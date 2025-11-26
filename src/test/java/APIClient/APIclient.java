package APIClient;
import static io.restassured.RestAssured.given;
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
}
