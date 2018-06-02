package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;

import java.util.List;

import com.rsr.domain.Request;
import com.rsr.domain.Table;

public class RunMeTestCodeWriter extends CodeWriter {

	private List<String> listOfStepDef;

	public RunMeTestCodeWriter(String projectName, List<String> listOfStepDef) {
		super(projectName);
		this.listOfStepDef = listOfStepDef;
	}
	
	@Override
	String getfilePath() {
		return DEST_DIR + projectName + "//run.bat";
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

//	@Override
//	public void writeCode() {
//		pr.println("\r\n" + 
//				"SET JAVA_HOME=\"C:\\Progra~1\\Java\\jdk1.8.0_161\"\r\n" + 
//				"cd " + projectPath + "\r\n"+
//				"mvn clean install\r\n" + 
//				"");
//		closePrinter();
//		// table.getTableClassName() + "Entity"
//	}
	
	// mvn -Dtest=stepDefination.MspEmployerTestsStepDefinitions test
	
	@Override
	public void writeCode() {
		
		List<String> tables = listOfStepDef;
		
		pr.println("\r\n" + 
				"SET JAVA_HOME=\"C:\\Progra~1\\Java\\jdk1.8.0_161\"\r\n");
		pr.println("ping -n 30 127.0.0.1 > nul\r\n");
		pr.println("mvn -Dtest=" + tables.get(0) + " test");
		
		
		closePrinter();
		
		// table.getTableClassName() + "Entity"
	} 

}
