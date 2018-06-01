package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;

public class RunMeCodeWriter extends CodeWriter {

	private String projectPath;

	public RunMeCodeWriter(String projectName, String projectPath) {
		super(projectName);
		this.projectPath = projectPath;
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
	
	@Override
	public void writeCode() {
		pr.println("\r\n" + 
				"set CATALINA_HOME=C:\\assignment\\apache-maven-3.5.2\r\n" + 
				"SET JAVA_HOME=\"C:\\Progra~1\\Java\\jdk1.8.0_161\"\r\n" + 
				"mvn clean install && mvn tomcat7:run");
		closePrinter();
		
		// table.getTableClassName() + "Entity"
	} 
	
}
