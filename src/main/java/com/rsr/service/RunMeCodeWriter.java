package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;

public class RunMeCodeWriter extends CodeWriter {

	public RunMeCodeWriter(String projectName) {
		super(projectName);
	}

	@Override
	String getfilePath() {
		return DEST_DIR + projectName + "//run.bat";
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
		pr.println("\r\n" + 
				"SET JAVA_HOME=\"C:\\Progra~1\\Java\\jdk1.8.0_161\"\r\n" + 
				"mvn clean install\r\n" + 
				"");
		closePrinter();
		
		// table.getTableClassName() + "Entity"
	}
}