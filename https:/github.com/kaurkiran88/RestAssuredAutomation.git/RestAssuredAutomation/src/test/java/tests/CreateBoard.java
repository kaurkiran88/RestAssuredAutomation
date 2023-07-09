package tests;

import base.TestBase;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateBoard extends TestBase{
	
	String boardID;
	String boardName = "Kiran's Board " + randomNum;
	String response;

	
	@BeforeClass
	public String postBoard() {
		
		baseURI = prop.getProperty("URL");
      	RequestSpecification httpRequest = given();
		httpRequest.header("Content-Type", "application/json");
		Response response = httpRequest.request(Method.POST, "/boards/?name="+boardName+"&key="+key+"&token="+token);
		
		ValidatableResponse responseLog = response.then().log().all();
		
		System.out.println(response.getBody().asString());
		
		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();
		
		boardID = response.jsonPath().get("id");
	
		return boardID;
		
		
	}
	
	@Test
	public void verifyStatusCodeforBoardCreation() {
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
	}
	
	@Test
	public void verifyStatusLineforBoardCreation() {
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, StatusLine_200);
	}
	

}
