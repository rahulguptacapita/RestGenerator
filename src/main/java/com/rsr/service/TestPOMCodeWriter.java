package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;

public class TestPOMCodeWriter extends CodeWriter {

	public TestPOMCodeWriter(String projectName) {
		super(projectName);
	}

	@Override
	String getfilePath() {
		return DEST_DIR + projectName + "//pom.xml";
		//return  ENTITY + "\\" + getClassName();
	}
	
	@Override
	String getClassName() {
		return "DBEntity";
	}

	@Override
	public void writepackage() {}

	@Override
	public void writeClass() {}

	@Override
	public void writeMethods() {}

	@Override
	public void writeVariables() {}

	@Override
	public void writeCode() {
		pr.println("<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\r\n" + 
				"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
				"	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n" + 
				"	<modelVersion>4.0.0</modelVersion>\r\n" + 
				"	<groupId>Automation</groupId>\r\n" + 
				"	<artifactId>"+ projectName +"</artifactId>\r\n" + 
				"	<version>0.0.1-SNAPSHOT</version>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<properties>\r\n" + 
				"		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\r\n" + 
				"		<cucumber.version>1.2.5</cucumber.version>\r\n" + 
				"		<extentreports.version>3.1.1</extentreports.version>\r\n" + 
				"	</properties>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<dependencies>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>info.cukes</groupId>\r\n" + 
				"			<artifactId>cucumber-java</artifactId>\r\n" + 
				"			<version>${cucumber.version}</version>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>org.seleniumhq.selenium</groupId>\r\n" + 
				"			<artifactId>selenium-java</artifactId>\r\n" + 
				"			<version>3.4.0</version>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>io.rest-assured</groupId>\r\n" + 
				"			<artifactId>rest-assured</artifactId>\r\n" + 
				"			<version>3.1.0</version>\r\n" + 
				"			<scope>test</scope>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>info.cukes</groupId>\r\n" + 
				"			<artifactId>cucumber-junit</artifactId>\r\n" + 
				"			<version>1.2.5</version>\r\n" + 
				"			<scope>test</scope>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>com.sun.jersey</groupId>\r\n" + 
				"			<artifactId>jersey-json</artifactId>\r\n" + 
				"			<version>1.8</version>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>junit</groupId>\r\n" + 
				"			<artifactId>junit</artifactId>\r\n" + 
				"			<version>4.12</version>\r\n" + 
				"			<scope>test</scope>\r\n" + 
				"		</dependency>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>com.aventstack</groupId>\r\n" + 
				"			<artifactId>extentreports</artifactId>\r\n" + 
				"			<version>3.1.2</version>\r\n" + 
				"		</dependency>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>com.vimalselvam</groupId>\r\n" + 
				"			<artifactId>cucumber-extentsreport</artifactId>\r\n" + 
				"			<version>3.0.0</version>\r\n" + 
				"		</dependency>\r\n" + 
				"	</dependencies>\r\n" + 
				"</project>");
		
		closePrinter();
		
		// table.getTableClassName() + "Entity"
	}


}
