import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test (dataProvider = "BooksData")
	
	public void AddBook() 
	{
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		
		String response = given().header("Content-Type", "application/json")
				
				.body(payLoad.AddBook("abcds", "245")) 
				
				.when().post("/Library/Addbook.php/?")
				.then().assertThat().statusCode(200).extract().response.toString;
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("id");
		System.out.println(id); 
		
		
		System.out.println("Change#1");
		System.out.println("Change#2");
	}

	@DataProvider (name="BooksData")	 
	
	public Object [][] getData() 
	
	{
		
		
		
		return new Object [][] {{"abcd","12345"}, {"esdd","5678"}}; 
				
	}
	
	
	}
		
		
		
				
		
	
	


