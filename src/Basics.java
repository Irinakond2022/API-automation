
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.ReUsableMethods;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;



public class Basics {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
//Validate if Add place API works as expected
		//given - all input details
		//when - submit the call , here is "http" method and resource
		//then -validate response
		
	RestAssured.baseURI ="https://rahulshettyacademy.com";
	String response = 
			
			given().log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(payLoad.AddPlace())
			
			.when().post("/maps/api/place/add/json")
			
			.then().assertThat().statusCode(200).body("scope", equalTo ("APP"))
			.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
	
	//System.out.println(response);
	
	JsonPath js = new JsonPath (response);
	
	String PlaceID = js.get("place_id");
	System.out.println(PlaceID);
	
	// Add Place -> Update with a new address -> Validate that new address is presented in response
	
	String NewAddress = "70 Summer walk, Africa";
	
	given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
	.body("{\n"
			+ "\"place_id\":\""+PlaceID+"\",\n" // we add dynamic variable in "" and contamination pluses from both sides
			+ "\"address\":\""+NewAddress+"\",\n"
			+ "\"key\":\"qaclick123\"\n"
			+ "}")
	.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	// Get Place
	
	String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
	.queryParam("place_id", PlaceID)
	.when().get("maps/api/place/get/json")
	.then().assertThat().log().all().statusCode(200).extract().asString();
	
	
	//JsonPath js1 = new JsonPath(getPlaceResponse);
	JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
	String actualAddress = js1.getString("address");
	
	System.out.println(actualAddress);


	//Write assertion
	
	
Assert.assertEquals(actualAddress, NewAddress);
	
	}


	}

	
	
	
