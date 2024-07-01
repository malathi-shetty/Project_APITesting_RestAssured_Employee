package com.employeeapi.testCases;



import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.baseClass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;



public class TC001_Get_All_Employees extends TestBase {
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("***** Started TC001_Get_All_Employees *******");
		
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(5000);
	}
	
	//Validations:
	@Test
	void checkResponseBody()
	{
		logger.info("******** Checking Response Body **********");
		String responseBody = response.getBody().asPrettyString();
		logger.info("Response Body == > " + responseBody);
		Assert.assertTrue(responseBody!= null);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("******* Checking Status Code*********");
		
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Status Code is ==> " + statusCode); //200
		Assert.assertEquals(statusCode, 200);
	}
	
	
	@Test
	void checkResponseTime()
	{
		logger.info("******** Checking Response Time **********");

long responseTime = response.getTime(); // Get status Line
logger.info("Response time is ==> " + responseTime);

if(responseTime > 2000)
	logger.warn("Response Time is greater than 2000");
Assert.assertTrue(responseTime<10000);

	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("***** Checking status Line *******");
		String statusLine = response.getStatusLine(); // Getting Status Line
		logger.info("Status Line is ==> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");		
	}
	
	@Test
	void checkContentType()
	{
		logger.info("******* Checking Content Type ********");
		
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==> " + contentType);
		Assert.assertEquals(contentType, "application/json");		
	}
	
	@Test
	void checkServerType()
	{
		logger.info("***** Checking Server Type *******");
		String serverType = response.header("Server");
		logger.info("*Server Type is ==> " + serverType);
		Assert.assertEquals(serverType, "nginx/1.21.6");
		
	}
	
	@Test
	void checkContentEncoding()
	{
		logger.info("***** Checking Content Encoding *******");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("*Content Encoding is ==> " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
	}
	
	@Test
	void checkContentLength()
	{
		logger.info(" ****** Checking Content Length ******* ");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==> " + contentLength);
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content Length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
		
	}
	
	@Test
	void checkCookies()
	{
		logger.info(" ****** Checking Cookies ******* ");
		String cookie = response.getCookie("PHPSESSID");//can chck cookies generate or not
		System.out.println("Cookie Generated ==> " + cookie);
		//Assert.assertEquals(cookie,""); // dynamic generates so couldnt check  since it runs on runtime
		
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("******* Finished TC001_Get_All_Employees **********");
	}
	

}
