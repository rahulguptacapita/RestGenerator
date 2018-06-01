/**
 * 
 */
package org.testingworld.automation.testcase;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/resources/functionalTests",
	glue= {"stepDefination"},
	plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
	)
 

public class TestRunner {
	
	@BeforeClass
	public static void setup() {
	    ExtentProperties extentProperties = ExtentProperties.INSTANCE;
	    extentProperties.setReportPath("output/myreport.html");
	}
	
	
	
//	@AfterClass
//	public static void writeExtentReport() {
//		Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
//	}
	
/*	@BeforeClass
    public static void setup() {
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
//        extentProperties.setExtentXServerUrl("http://localhost:1337");
//        extentProperties.setProjectName("MyProject");
        extentProperties.setReportPath("output/myreport.html");
    }

    @AfterClass
    public static void teardown() {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Mac OSX");
        Reporter.setTestRunnerOutput("Sample test runner output message");
    }*/
	   /*@AfterClass
	    public static void teardown() {
	        Reporter.loadXMLConfig(new File("AutomationProject/Driver/extent-config.xml"));
	        Reporter.setSystemInfo("user", System.getProperty("user.name"));
	        Reporter.setSystemInfo("os", "Win");
	        Reporter.setTestRunnerOutput("Sample test runner output message");
	    }*/
}