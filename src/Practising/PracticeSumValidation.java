
package Practising;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import files.payLoad;
import io.restassured.path.json.JsonPath;

public class PracticeSumValidation {

	
	public void SumOfCourses() {
		
		int sum = 0;
		
		JsonPath js = new JsonPath(payLoad.CoursePrice());
		
	     int CourseSize=js.get("courses.size()");
	
 for (int i=0; i<CourseSize; i++) {
	 
	 
	 int coursesPrice = js.getInt("courses["+i+"].price");
	 int numberOfCopies = js.getInt("courses["+i+"].copies");
	 int TotalAmount = coursesPrice * numberOfCopies;
	 
	
	sum = sum+TotalAmount; 

 }
 System.out.println(sum);	 

 int expectedPurchaseAmount = js.get("dashboard.purchaseAmount");

assert.assertEquals(sum, expectedPurchaseAmount);

	}

}
