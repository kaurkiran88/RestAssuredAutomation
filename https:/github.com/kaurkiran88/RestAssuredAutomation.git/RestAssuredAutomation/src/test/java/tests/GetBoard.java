package tests;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetBoard extends TestBase{
	
	CreateBoard cb = new CreateBoard();
	String boardName;
	String boardID = cb.postBoard();
	
	@BeforeClass
	public void getBoard() {
		
		
		baseURI = prop.getProperty("URL");
      	RequestSpecification httpRequest = given();
		httpRequest.header("Content-Type", "application/json");
		Response response = httpRequest.request(Method.GET, "boards/"+boardID+"?key="+key+"&token="+token);
		
		ValidatableResponse responseLog = response.then().log().all();
		
		System.out.println(response.getBody().asString());
		
		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();
		boardName = response.jsonPath().get("name");
		
		
		
	}
	
	@Test
	public void verifyStatusCodeforGetBoard() {
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
	}
	
	@Test
	public void verifyStatusLineforGetBoard() {
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, StatusLine_200);
	}
	
	@Test
	public void verifyBoardNameforGetBoard() {
		//System.out.println(boardID);
		System.out.println(boardName);
		Assert.assertEquals(boardName, cb.boardName);
		
	}


}
