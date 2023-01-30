package Practising;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.expectThrows;

import files.payLoad;


public class Basics_practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payLoad.AddPlace())
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).header("Server", "Apache/2.4.41 (Ubuntu)")
				.body("scope", equalTo ("APP")).extract().response().asString();
		
		System.out.println(response);
	}

}
