package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;
import static com.rsr.ServiceConstants.TESTS;
import static com.rsr.ServiceConstants.TEST_CONFIG_DIR;

import com.rsr.domain.Method;
import com.rsr.domain.Table;

public class FeatureCodeWriter extends CodeWriter {

	public FeatureCodeWriter(Table table, String projectName) {
		super(table, projectName);
	}

	@Override
	String getfilePath() {
		return DEST_DIR + projectName + TEST_CONFIG_DIR + "\\" + getClassName() + ".feature";
	}

	@Override
	String getClassName() {
		return table.getTableClassName() + TESTS;
	}
	
	@Override
	public void writepackage() {}

	@Override
	protected void writeImport() {}

	@Override
	public void writeClass() {}

	@Override
	public void writeVariables() {}

	@Override	
	public void writeMethods() {
		
		pr.println("Feature: Testing Rest Assure\r\n" + 
				"Background: \r\n" + 
				"	Given I test the get Api\r\n" + 
				"	\r\n" + 
				"Scenario Outline: Testing all the Api methods\r\n" + 
				"\r\n" + 
				" When I test the generated api calls for getById method\r\n" + 
				" Then I test the generated api calls for post method\r\n" + 
				" And I test the generated api calls for put method\r\n" + 
				" And I test the api for delete method");
	}


	
	private void putWrite() {
	}

	private void deleteWrite() {
	}

	private void postWrite() {
	}

	private void getByIdWrite() {
	}

	private void getwrite() {
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
