package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class UpdateBoardName extends TestBase{
	
	CreateBoard cb = new CreateBoard();
	String boardID = cb.postBoard();
	String newBoardName = "Kiran Board Updated";
	String BoardNameAfterUpdate;
	
	@BeforeClass
	public void updateBoard() {
		
		baseURI = prop.getProperty("URL");
      	RequestSpecification httpRequest = given();
      	httpRequest.header("Content-Type", "application/json");
      	Response response = httpRequest.request(Method.PUT, "boards/"+boardID+"?name="+newBoardName+"&key="+key+"&token="+token);
      	
      	ValidatableResponse responseLog = response.then().log().all();  
	
		System.out.println(response.getBody().asString());
		
		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();
		BoardNameAfterUpdate = response.jsonPath().get("name");
	}
	
	@Test
	public void verifyStatusCodeforUpdateBoardName() {
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
	}
	
	@Test
	public void verifyStatusLineforUpdateBoardName() {
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, StatusLine_200);
	}
	
	@Test
	public void verifyNameUpdateforUpdateBoardName() {
		System.out.println(BoardNameAfterUpdate);
		Assert.assertEquals(BoardNameAfterUpdate, newBoardName);
		
	}

}
