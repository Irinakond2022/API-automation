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
				
				.body(payLoad.AddBook("abcds", "245")) //example with one set of parameters
				
				.when().post("/Library/Addbook.php/?")
				.then().assertThat().statusCode(200).extract().response.toString;
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("id");
		System.out.println(id); 
		/*but before running test, you should change 'aisle' value to another so that test passes posting a new book */
		
	
	}

	@DataProvider (name="BooksData")	 // this is to get data from external file and put a name for it for adding it to our test above.
	
	public Object [][] getData() // delete VOID (because its used when no return) and put 'Object [][]'
	
	{
		
		//array = collection of elements
		//multidimensional array = collection of arrays
		
		return new Object [][] {{"abcd","12345"}, {"esdd","5678"}}; //this is one dimenstion array. If object [][] - multi-dimensional array
				
	}
	
	
	}
		
		
		
				
		
	
	


