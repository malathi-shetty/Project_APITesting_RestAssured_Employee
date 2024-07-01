package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Listeners extends TestListenerAdapter 
{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext) 
	{
	//ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
// htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/myListenerReports.html");//specify location
		htmlReporter=new ExtentHtmlReporter("D:/User/practice/Project_APITesting_RestAssured_Employee/Reports/myListenerReports.html");
	htmlReporter.config().setDocumentTitle("Automation Report");// Title of the Report
	htmlReporter.config().setReportName("Rest API Testing Report");// Name of the Report
	//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // Location of the chart
	htmlReporter.config().setTheme(Theme.DARK);
	
	extent = new ExtentReports();
	extent.attachReporter(htmlReporter);
	extent.setSystemInfo("Project Name", "Rest API Testing");
	extent.setSystemInfo("Host name", "localhost");
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user", "Malathi");
	}
	
	
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getClass().getName());
		test.createNode(result.getName());
		test = extent.createTest(result.getName()); // create new entry in th report
		
		test.log(Status.PASS, "Test Case PASSED IS " + result.getName());

	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getName()); // create new entry in th report
		
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in exent report
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent reports
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getName()); // create new entry in th report	
		test.log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getName()); // to add name in exent report
	}
	
	public void onFinish(ITestResult testContext)
	{
		extent.flush();
	}

}