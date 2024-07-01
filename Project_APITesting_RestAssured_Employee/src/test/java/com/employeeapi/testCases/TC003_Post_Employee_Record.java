
//TestName: Create new employee Record in database

package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.baseClass.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Employee_Record  extends TestBase{
	

	RequestSpecification httpRequest;
	Response response;

	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("***** Started TC003_Post_Employee_Record *******");
		
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		//JSONObject is a class that represents a simple JSON.
		//We can add Key-Value pairs using the put method
		//{"name":"John123X","salary":"123","age":"23"}
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");
		
		//Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.POST,"/create");
		Thread.sleep(8000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("******** Checking Response Body **********");
		String responseBody = response.getBody().asPrettyString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}

	
	@Test
	void checkStatusCode()
	{
		logger.info("******* Checking Status Code*********");
		
		int statusCode = response.getStatusCode(); // Getting status code
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	
	
	
	@Test
	void checkStatusLine()
	{
		logger.info("***** Checking status Line *******");
		String statusLine = response.getStatusLine(); // Getting Status Line
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");		
	}
	
	@Test
	void checkContentType()
	{
		logger.info("******* Checking Content Type ********");
		
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==> " + contentType);
		System.out.println(contentType);
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
		logger.info("******* Finished TC003_Post_Employee_Record **********");
	}
	

}
