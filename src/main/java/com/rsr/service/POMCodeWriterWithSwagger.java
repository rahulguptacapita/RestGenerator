package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;

public class POMCodeWriterWithSwagger extends CodeWriter  {
	
	public POMCodeWriterWithSwagger(String projectName) {
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
				"	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\r\n" + 
				"	<modelVersion>4.0.0</modelVersion>\r\n" + 
				"	<groupId>com.rsr</groupId>\r\n" + 
				"	<artifactId>"+ projectName +"</artifactId>\r\n" + 
				"	<packaging>war</packaging>\r\n" + 
				"	<version>1.0-SNAPSHOT</version>\r\n" + 
				"	<name>RestGenerator Generated App</name>\r\n" + 
				"	<url>http://maven.apache.org</url>\r\n" + 
				"\r\n"); 
				
     
		pr.println("	<repositories>\r\n" + 
				"		<repository>\r\n" + 
				"			<id>maven2-repository.java.net</id>\r\n" + 
				"			<name>Java.net Repository for Maven</name>\r\n" + 
				"			<url>http://download.java.net/maven/2/</url>\r\n" + 
				"			<layout>default</layout>\r\n" + 
				"		</repository>\r\n" + 
				"	</repositories>\r\n" + 
				"\r\n" + 
				"	<dependencies>\r\n" + 
				"\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>com.sun.jersey</groupId>\r\n" + 
				"			<artifactId>jersey-server</artifactId>\r\n" + 
				"			<version>1.8</version>\r\n" + 
				"		</dependency>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>com.sun.jersey</groupId>\r\n" + 
				"			<artifactId>jersey-json</artifactId>\r\n" + 
				"			<version>1.8</version>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"		<!-- https://mvnrepository.com/artifact/com.microsoft.sqlserver/sqljdbc4 -->\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>com.microsoft.sqlserver</groupId>\r\n" + 
				"			<artifactId>sqljdbc4</artifactId>\r\n" + 
				"			<version>4.0</version>\r\n" + 
				"			<scope>test</scope>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"		<!-- https://mvnrepository.com/artifact/net.sourceforge.jtds/jtds -->\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>net.sourceforge.jtds</groupId>\r\n" + 
				"			<artifactId>jtds</artifactId>\r\n" + 
				"			<version>1.2.7</version>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		<!-- https://mvnrepository.com/artifact/junit/junit -->\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>junit</groupId>\r\n" + 
				"			<artifactId>junit</artifactId>\r\n" + 
				"			<version>4.11</version>\r\n" + 
				"			<scope>test</scope>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"		<!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>io.rest-assured</groupId>\r\n" + 
				"			<artifactId>rest-assured</artifactId>\r\n" + 
				"			<version>3.1.0</version>\r\n" + 
				"			<scope>test</scope>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	</dependencies>\r\n" + 
				"\r\n" + 
				"	<pluginRepositories>\r\n" + 
				"		<pluginRepository>\r\n" + 
				"			<id>ossrh-snapshots</id>\r\n" + 
				"			<url>https://oss.sonatype.org/content/repositories/snapshots/\\</url>\r\n" + 
				"			<releases>\r\n" + 
				"				<enabled>false</enabled>\r\n" + 
				"			</releases>\r\n" + 
				"			<snapshots>\r\n" + 
				"				<enabled>true</enabled>\r\n" + 
				"			</snapshots>\r\n" + 
				"		</pluginRepository>\r\n" + 
				"	</pluginRepositories>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<build>\r\n" +
				"	<finalName>${project.artifactId}-${project.version}</finalName>\r\n" + 
				"							<plugins>\r\n" + 
				"								<plugin>\r\n" + 
				"									<groupId>org.apache.tomcat.maven</groupId>\r\n" + 
				"									<artifactId>tomcat7-maven-plugin</artifactId>\r\n" + 
				"									<version>2.2</version>\r\n" + 
				"								</plugin>\r\n" + 
				"								<plugin>\r\n" + 
				"									<artifactId>maven-compiler-plugin</artifactId>\r\n" + 
				"									<configuration>\r\n" + 
				"										<source>1.8</source>\r\n" + 
				"										<target>1.8</target>\r\n" + 
				"									</configuration>\r\n" + 
				"								</plugin>\r\n" + 
				"								<plugin>\r\n" + 
				"									<groupId>com.sebastian-daschner</groupId>\r\n" + 
				"									<artifactId>jaxrs-analyzer-maven-plugin</artifactId>\r\n" + 
				"									<version>0.13</version>\r\n" + 
				"									<executions>\r\n" + 
				"										<execution>\r\n" + 
				"											<goals>\r\n" + 
				"												<goal>analyze-jaxrs</goal>\r\n" + 
				"											</goals>\r\n" + 
				"										</execution>\r\n" + 
				"									</executions>\r\n" + 
				"									<configuration>\r\n" + 
				"										<backend>swagger</backend>\r\n" + 
				"									</configuration>\r\n" + 
				"								</plugin>\r\n" + 
				"							</plugins>\r\n" +
				"	</build>\r\n" + 
				"\r\n" + 
				"</project>");
		
		closePrinter();
		
		// table.getTableClassName() + "Entity"
	}

}
