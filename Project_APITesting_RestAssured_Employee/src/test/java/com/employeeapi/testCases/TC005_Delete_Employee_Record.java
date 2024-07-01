package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.baseClass.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException
	{
		logger.info(" ***** Started TC005_Delete_Employee_Record ****** ");
		
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET, "employees");
		
		//First get the JsonPath object instance from the Response Interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		// Log or print the raw response body for inspection
		String responseBody = response.getBody().asString();
		System.out.println("Response Body: " + responseBody);
		
		
		//Capture id
		// Check if response body is valid JSON before further processing
		if (responseBody.isEmpty() || !responseBody.startsWith("{")) {
		    // Handle the case where response is empty or not JSON
		    System.err.println("Invalid JSON response or empty response.");
		} else {
		    // Proceed with JSON parsing
		
			String empID = jsonPathEvaluator.get("data[2].id").toString();
			System.out.println(empID);
		
	//	return jsonPathEvaluator.get("user_id").toString();
		

	
		response = httpRequest.request(Method.DELETE, "/delete/" + empID); // pass ID to delete record
		
		Thread.sleep(10000);
		}	
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response Body: " + responseBody);
		Assert.assertEquals(responseBody.contains("Successfully! Record has been deleted"), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Getting status code
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine()
	{
		String statusLine = response.getStatusLine(); // Getting status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}
	
	@Test
	void checkServerType()
	{
		logger.info("***** Checking Server Type *******");
		String serverType = response.header("Server");
		System.out.println(serverType);
		Assert.assertEquals(serverType, "Apache");
		
	}
	
	@Test
	void checkContentEncoding()
	{
		logger.info("***** Checking Content Encoding *******");
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
		
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info(" ***** Completed TC005_Delete_Employee_Record ****** ");
	}
	

}
