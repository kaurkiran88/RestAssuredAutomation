package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetLists extends TestBase{
	
	public List<Object> listID;
	
	@BeforeClass
	public List<Object> getLists() {
		
		CreateBoard cb = new CreateBoard();
		String boardID = cb.postBoard();
		
		baseURI = prop.getProperty("URL");
      	RequestSpecification httpRequest = given();
      	httpRequest.header("Content-Type", "application/json");
      	Response response = httpRequest.request(Method.GET, "boards/"+boardID+"/lists?key="+key+"&token="+token);
      	
      	ValidatableResponse responseLog = response.then().log().all();  
	
		System.out.println(response.getBody().asString());
		
		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();
		
		System.out.println(response.jsonPath().getList("name"));
		listID = response.jsonPath().getList("id");
		
		return listID;
		
	}

	@Test
	public void verifyStatusCodeforGetLists() {
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
	}
	
	@Test
	public void verifyStatusLineforGetLists() {
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, StatusLine_200);
	}
}
