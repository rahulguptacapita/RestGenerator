package org.testingworld.automation.testcase;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/org/testingworld/auatomation/assertion/loginTestCase.feature",
glue= {"stepDefinitions"},
plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
monochrome = true)


public class Cukes {

}
