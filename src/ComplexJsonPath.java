import files.payLoad;
import io.restassured.path.json.JsonPath;


public class ComplexJsonPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payLoad.CoursePrice());
		
	//	1. Print No of courses returned by API

int count = js.get("courses.size()");
System.out.println(count);

//2.Print Purchase Amount

int totalAmount = js.getInt("dashboard.purchaseAmount");
System.out.println(totalAmount);
//3. Print Title of the first course

String titleFirstCourse = js.get("courses[0].title");
System.out.println(titleFirstCourse);
	

//4. Print All course titles and their respective Prices

	for (int i=0; i<count; i++) 
	
	{
		
	String courseTitles = js.get("courses["+i+"].title");
	System.out.println(courseTitles);
	System.out.println(js.get("courses["+i+"].price").toString()); 
	
	}
	//5. Get number of copies for RSA course
	
	System.out.println("Get number of copies for RSA course");
	
	for (int i=0; i<count; i++) 
		
	{
		
	String courseTitles = js.get("courses["+i+"].title");
	
	if (courseTitles.equalsIgnoreCase("RPA")) {
		
		int numberCopiesRPA = js.get("courses["+1+"].copies");
		System.out.println(numberCopiesRPA);
		break; 
	}
	
	
  }
	
	
	
	
 }
	
	
}



