package com.employeeapi.baseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="10"; //Hardcoded Input for get Details of single employee & update employee
	
	
	public Logger logger;
	
	@BeforeClass
	public void setup() throws FileNotFoundException, IOException
	{

		logger = LogManager.getLogger("EmployeesRestAPI"); // added logger
	//	propertyConfigurator.configure( log4j.properties ); // added logger
			ConfigurationSource source = new ConfigurationSource(new FileInputStream(System.getProperty("user.dir")+"//log4j.properties"));
		// 	logger.setLevel(Level.DEBUG);
			logger.atLevel(Level.DEBUG);
	
		// Example usage of the logger
	    logger.info("Logging initialized programmatically");
	    logger.trace("We've just traced the user!");
	    logger.debug("We've just debugged the user!");
	    logger.info("We've just got info of the user!");
	    logger.warn("We've just warned the user!");
	    logger.error("We've just found error in the user!");
	    logger.fatal("We've just fatal the user!");
		
	}

}
