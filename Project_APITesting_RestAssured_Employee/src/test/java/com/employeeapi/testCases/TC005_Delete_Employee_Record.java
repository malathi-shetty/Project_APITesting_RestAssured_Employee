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
	
	private RequestSpecification httpRequest;
    private Response response;

    @BeforeClass
    void setUp() throws InterruptedException {
        // Initialize and configure RestAssured
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        // Fetch employee data to delete
        response = httpRequest.request(Method.GET, "/employees");

        // Parse response to extract employee ID
        JsonPath jsonPathEvaluator = response.jsonPath();
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        if (responseBody.isEmpty() || !responseBody.startsWith("{")) {
            System.err.println("Invalid JSON response or empty response.");
        } else {
            String empID = jsonPathEvaluator.get("data[2].id").toString();
            System.out.println("Employee ID: " + empID);

            // Delete the employee with the extracted ID
            response = httpRequest.request(Method.DELETE, "/delete/" + empID);
        }

        Thread.sleep(10000); // Ensure the delete operation completes
    }

    @Test
    void checkResponseBody() {
        String responseBody = response.getBody().asPrettyString();
        System.out.println("Response Body: " + responseBody);
        Assert.assertTrue(responseBody.contains("Successfully! Record has been deleted"));
    }

    @Test
    void checkStatusCode() {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void checkStatusLine() {
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    void checkContentType() {
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json");
    }

    @Test
    void checkServerType() {
        String serverType = response.header("Server");
        System.out.println("Server Type: " + serverType);
        Assert.assertEquals(serverType, "Apache");
    }

    @Test
    void checkContentEncoding() {
        String contentEncoding = response.header("Content-Encoding");
        Assert.assertEquals(contentEncoding, "gzip");
    }

    @AfterClass
    void tearDown() {
        System.out.println(" ***** Completed TC005_Delete_Employee_Record ****** ");
    }

}
