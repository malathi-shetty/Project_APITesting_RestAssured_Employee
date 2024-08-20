package com.employeeapi.testCases;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record {

    private static final Logger logger = LoggerFactory.getLogger(TC002_Get_Single_Employee_Record.class);

    private static final String BASE_URI = "https://dummy.restapiexample.com/api/v1";
    private static final String EMPLOYEE_ENDPOINT = "/employee/";
    private RequestSpecification httpRequest;
    private Response response;
    private String empID = "1"; // Example ID, replace with actual value

    @BeforeClass
    void getEmployeeData() throws InterruptedException {
        logger.info("***** Started TC002_Get_Single_Employee_Record *******");

        // Setup base URI and request
        RestAssured.baseURI = BASE_URI;
        httpRequest = RestAssured.given();

        // Send GET request
        response = httpRequest.request(Method.GET, EMPLOYEE_ENDPOINT + empID);
        Thread.sleep(5000);
    }

    @Test
    void checkResponseBody() {
        logger.info("******** Checking Response Body **********");
        String responseBody = response.getBody().asPrettyString();
        logger.info("Response Body == > " + responseBody);
        Assert.assertTrue(responseBody.contains(empID), "Response body does not contain employee ID");
    }

    @Test
    void checkStatusCode() {
        logger.info("******* Checking Status Code *********");
        int statusCode = response.getStatusCode(); // Getting status code
        logger.info("Status Code is ==> " + statusCode);
        Assert.assertEquals(statusCode, 200, "Status code is not 200");
    }

    @Test
    void checkResponseTime() {
        logger.info("******** Checking Response Time **********");
        long responseTime = response.getTime(); // Get response time
        logger.info("Response time is ==> " + responseTime);
        Assert.assertTrue(responseTime < 6000, "Response time is greater than 6000ms");
    }

    @Test
    void checkStatusLine() {
        logger.info("***** Checking Status Line *******");
        String statusLine = response.getStatusLine(); // Getting status line
        logger.info("Status Line is ==> " + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Status line is not as expected");
    }

    @Test
    void checkContentType() {
        logger.info("******* Checking Content Type ********");
        String contentType = response.header("Content-Type");
        logger.info("Content Type is ==> " + contentType);
        Assert.assertEquals(contentType, "application/json", "Content type is not application/json");
    }

    @Test
    void checkServerType() {
        logger.info("***** Checking Server Type *******");
        String serverType = response.header("Server");
        logger.info("Server Type is ==> " + serverType);
        Assert.assertEquals(serverType, "nginx/1.21.6", "Server type is not as expected");
    }

    @Test
    void checkContentLength() {
        logger.info("****** Checking Content Length *******");
        String contentLength = response.header("Content-Length");
        logger.info("Content Length is ==> " + contentLength);
        Assert.assertTrue(Integer.parseInt(contentLength) < 1500, "Content length is not less than 1500");
    }

    @AfterClass
    void tearDown() {
        logger.info("******* Finished TC002_Get_Single_Employee_Record **********");
    }
}
