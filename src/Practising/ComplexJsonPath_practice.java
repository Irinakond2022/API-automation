package Practising;

import files.payLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonPath_practice {
	
	
	
//1. Print No of courses returned by API
		
		 JsonPath js = new JsonPath(payLoad.CoursePrice());
		 int number = js.get("courses.size()");
		 System.out.printLn(number);
		 


	
		
//2.Print Purchase Amount
		
	
	
	
	
	
	
		
//3. Print Title of the first course
		
		
	
	

//4. Print All course titles and their respective Prices


	
	
	
	
//5. Get number of copies for RSA course
 
		
	
	
	}



