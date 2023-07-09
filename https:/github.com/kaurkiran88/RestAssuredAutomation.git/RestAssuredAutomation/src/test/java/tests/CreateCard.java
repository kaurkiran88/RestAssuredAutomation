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

public class CreateCard extends TestBase{
	
	public String cardID;
	String cardName = "Kiran's Card " + randomNum;
	
	GetLists gl = new GetLists();
	Object listID = gl.getLists().get(1);
	
	@BeforeClass
	public String createCard() {
		
		baseURI = prop.getProperty("URL");
      	RequestSpecification httpRequest = given();
      	httpRequest.header("Content-Type", "application/json");
      	Response response = httpRequest.request(Method.POST, "cards/?idList="+listID+"&name="+cardName+"&key="+key+"&token="+token);
      	
      	ValidatableResponse responseLog = response.then().log().all();
      
      	System.out.println(response.getBody().asString());
      	
		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();
		
		cardID = response.jsonPath().get("id");
	
		return cardID;
	}
	
	@Test
	public void verifyStatusCodeforCardCreation() {
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
	}
	
	@Test
	public void verifyStatusLineforCardCreation() {
		System.out.println(statusLine);
		System.out.println(cardID);
	}

}
