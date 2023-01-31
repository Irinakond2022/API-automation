import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import java.io.File;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import files.ReUsableMethods;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;



public class javaTest {

	public static void main(String[] args) {
		
RestAssured.baseURI = "http://localhost:8080/";



//Here we need to create Login script with SessionID

SessionFilter session = new SessionFilter(); //one of ways how to get Session which cnahges in response when we hit Login request


String response = given().header("Content-Type", "application/json").body("{ \"username\": \"irina_ka\", \"password\": \"mypassword\" }")
.filter(session).when().post("/rest/auth/1/session?").then().extract().response().toString();




//Add comment

String expectedmsg = "Comment here...";

String AddCommentResponse = given().pathParam("key", "25432").body("{\n"
		+ "    \"body\": \""+expectedmsg+"\",\n"
		+ "    \"visibility\": {\n"
		+ "        \"type\": \"role\",\n"
		+ "        \"value\": \"Administrators\"\n"
		+ "    }\n"
		+ "}").header("Content-Type", "application/json")//Add SessionFilter in your test
.filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201)
.extract().response().asString();



//This is for CASE#3 when we assert ID of comment and body
JsonPath js = new JsonPath(AddCommentResponse);
String commentID = js.get("id");


	
	//Add attachment
	

	given()
	.header("X-Atlassian-Token", "no-check")
	.pathParam("key", "25432")
	.filter(session)
	.header("Content-Type", "multipart/form-data")
	.multiPart("file", new File ("Java.txt"))
	.when().post("/rest/api/2/issue/{key}/attachments").then().assertThat().statusCode(200);
		
	
	
	
	//Get issue and Part2: lets restrict response by 'Comment' field only
	
	String IssueDetails = given().filter(session).pathParam("key", "25432")
	.queryParam("fields", "comment") //here we specify field by which we restrcit our response
	.when().get("/rest/api/2/issue/{key}")
	.then().extract().response().toString();
	System.out.print(IssueDetails);
	
	
	
	
	/*CASE #3: Now lets assert that our added comment (which has unique ID) is presented in response json
	As our comment is in an arrya (in json it looks like comments[5], we need to iterate the each index and 
verify if each index matches our ID).
1. Step. Get size of all array and how many comments we have.


*/
	
	
	JsonPath js1 = new JsonPath(IssueDetails);
	int CommentsCount = js1.getInt("fields.comments.comments.size()");
	
	for (int i= 0; i < CommentsCount; i++)	
	
	{
		String commentIDIssue = js.get("fields.comments.comments["+i+"]").toString();
		/*to print out all results use
		System.out.println(js.getInt("fields.comments.comments["+i+"]"));  */
	   if(commentIDIssue.equalsIgnoreCase(commentID)) {
		   
		   
	   }
		String messageOfComment = js.get("fields.comments.comments["+i+"].body").toString();
		assert.assertEquals(messageOfComment, expectedmsg);
	   }
	
	
	
	}

}
