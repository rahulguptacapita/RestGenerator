package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;
import static com.rsr.ServiceConstants.TEST_CUKES_DIR;

public class CukesCodeWriter extends CodeWriter {

	private String featureFileName;

	public CukesCodeWriter(String featureFileName, String projectName) {
		super(null, projectName);
		this.featureFileName = featureFileName;
	}
	
	@Override
	String getfilePath() {
		return DEST_DIR + projectName + TEST_CUKES_DIR + "\\" + getClassName() + ".java";
	}

	@Override
	String getClassName() {
		return "Cukes";
	}
	
	@Override
	public void writepackage() {
		pr.println("package org.testingworld.automation.testcase;");
	}

	@Override
	protected void writeImport() {
		pr.println("import org.junit.runner.RunWith;\r\n" + 
				"import cucumber.api.CucumberOptions;\r\n" + 
				"import cucumber.api.junit.Cucumber;");
	}

	@Override
	public void writeClass() {}

	@Override
	public void writeVariables() {}

	@Override
	public void writeMethods() {
		pr.println("@RunWith(Cucumber.class)\r\n" + 
				"@CucumberOptions(features = \"src/test/java/org/testingworld/auatomation/assertion/"+ featureFileName +"\",\r\n" + 
				"glue= {\"stepDefinitions\"},\r\n" + 
				"plugin = { \"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html\"},\r\n" + 
				"monochrome = true)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"public class Cukes {\r\n" + 
				"\r\n");
	}

	@Override
	public void writeCode() {
		writepackage();
		writeImport();
		blankLine();
		writeClass();
		blankLine();
		writeVariables();
		blankLine();
		writeMethods();
		blankLine();
		writeEndLine();
		closePrinter();
	}
	
}
