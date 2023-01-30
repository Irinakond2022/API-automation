import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.ReUsableMethods;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

//we need to convert data from .json file to String - > convert firstly to Byte - then to String
//by method Files.ReadAllBytes

public class GetDataFromExternalFile {


	public static void main(String[] args) {
		
		//Validate if Add place API works as expected
		//given - all input details
		//when - submit the call , here is "http" method and resource
		//then -validate response
		
	RestAssured.baseURI ="https://rahulshettyacademy.com";
	String response = 
			
			 given().log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(new String(Files.readAllBytes(Paths.get("file:///Users/irinakondriukova/Downloads/AddBook.json"))))
			
			.when().post("/maps/api/place/add/json")
			
			.then().assertThat().statusCode(200).body("scope", equalTo ("APP"))
			.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
	}
}