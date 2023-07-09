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

public class DeleteCard extends TestBase{
	
	CreateCard cl = new CreateCard();
	String cardID = cl.createCard();
	String cardIDafterDEL;
	
	@BeforeClass
	public void deleteCard() {
		
		baseURI = prop.getProperty("URL");
      	RequestSpecification httpRequest = given();
      	httpRequest.header("Content-Type", "application/json");
      	Response response = httpRequest.request(Method.DELETE, "cards/"+cardID+"?key="+key+"&token="+token);
      	
      	ValidatableResponse responseLog = response.then().log().all();
      
      	System.out.println(response.getBody().asString());
      	
		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();
		
		cardIDafterDEL = response.jsonPath().get("id");
	
	}
	
	@Test
	public void verifyStatusCodeforDeleteCard() {
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
	}
	
	@Test
	public void verifyStatusLineforDeleteCard() {
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, StatusLine_200);
	}
	
	@Test
	public void verifycardNotPresentforDeleteCard() {
		System.out.println(cardIDafterDEL);
		Assert.assertEquals(cardIDafterDEL, null);
		
	}

}
