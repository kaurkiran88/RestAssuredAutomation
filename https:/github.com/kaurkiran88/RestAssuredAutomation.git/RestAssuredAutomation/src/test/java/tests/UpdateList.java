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

public class UpdateList extends TestBase{
	
	String newListName = "Kiran's first list";
	String ListNameAfterUpdate;
	GetLists gl = new GetLists();
	Object listID = gl.getLists().get(0);
	
	@BeforeClass
	public void updateList() {
		
		baseURI = prop.getProperty("URL");
      	RequestSpecification httpRequest = given();
      	httpRequest.header("Content-Type", "application/json");
      	Response response = httpRequest.request(Method.PUT, "lists/"+listID+"?name="+newListName+"&key="+key+"&token="+token);
  
      	ValidatableResponse responseLog = response.then().log().all();
      	
      	System.out.println(response.getBody().asString());
     
		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();
	
		ListNameAfterUpdate = response.jsonPath().get("name");
	}
	
	@Test
	public void verifyStatusCodeforUpdateList() {
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
	
		
	}
	
	@Test
	public void verifyStatusLineforUpdateList() {
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, StatusLine_200);
	}

	@Test
	public void verifyNameUpdateforUpdateList() {
		System.out.println(ListNameAfterUpdate);
		Assert.assertEquals(ListNameAfterUpdate, newListName);
		
	}

}
