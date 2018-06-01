package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;
import static com.rsr.ServiceConstants.TESTS;
import static com.rsr.ServiceConstants.TEST_TEST_DIR;

import com.rsr.domain.Column;
import com.rsr.domain.Method;
import com.rsr.domain.Table;

public class StepDefinationCodeWriter extends CodeWriter {

	public StepDefinationCodeWriter(Table table, String projectName) {
		super(table, projectName);
	}

	// C:\kipo\exsample\ExtentWorkingwidMP\Reporting\src\test\java\stepDefination
	
	@Override
	String getfilePath() {
		return DEST_DIR + projectName + TEST_TEST_DIR + "\\" + getClassName() + ".java";
	}

	@Override
	String getClassName() {
		return table.getTableClassName() + TESTS + "StepDefinitions";
	}
	
	@Override
	public void writepackage() {
		pr.println("package stepDefination;");
	}

	@Override
	protected void writeImport() {
		pr.println("import org.codehaus.jettison.json.JSONException;\r\n" + 
				"import org.codehaus.jettison.json.JSONObject;\r\n" + 
				"import org.junit.Assert;\r\n" + 
				"import org.junit.Test;\r\n" + 
				"\r\n" + 
				"import cucumber.api.java.en.And;\r\n" + 
				"import cucumber.api.java.en.Given;\r\n" + 
				"import cucumber.api.java.en.Then;\r\n" + 
				"import cucumber.api.java.en.When;\r\n" + 
				"import io.restassured.RestAssured;\r\n" + 
				"import io.restassured.http.Method;\r\n" + 
				"import io.restassured.response.Response;\r\n" + 
				"import io.restassured.specification.RequestSpecification;");
	}

	@Override
	public void writeClass() {
		pr.println("public class "+ getClassName() +" {");
	}

	@Override
	public void writeVariables() {
		
		pr.println("private static final String RESTPATH = \""+ table.getRestPath() + "\";\r\n" + 
				"	private static final String BASEURL = \"http://localhost:8080/"+ projectName +"/rest/\";"
				+ "private static final String ID = \"\";");
	}

	@Override
	public void writeMethods() {
		
		for (Method req : table.getRequests()) {
			switch (req) {
			case GET:
				getwrite();
				break;
			case POST:
				postWrite();
				break;
			case GETBYID:
				getByIdWrite();
				break;
			case DELETE:
				deleteWrite();
				break;
			case PUT:
				putWrite();
				break;
			default:
				break;
			}
			
		}
	}
	


	private void putWrite() {
		pr.println("	@Test\r\n" + 
				"	@And(\"I test the generated api calls for put method\")\r\n" + 
				"	public void putTest() throws JSONException {\r\n" + 
				"		\r\n" + 
				"		\r\n" + 
				"		RestAssured.baseURI =  BASEURL + RESTPATH + ID;\r\n" + 
				"		RequestSpecification httpRequest = RestAssured.given();\r\n" + 
				"		\r\n" + 
				"		JSONObject requestParams = new JSONObject();\r\n" + 
				"		\r\n"); 
		for (Column column : table.getColumns()) {
			if(table.getPrimaryKey().equalsIgnoreCase(column.getColumnname())) {
				continue;
			}
			pr.println("		requestParams.put(\""+ column.getColumnname() +"\", \"\");");
		}		
				
		pr.println("		httpRequest.body(requestParams.toString());\r\n" + 
				"		Response response = httpRequest.request(Method.PUT);\r\n" + 
				"		\r\n" + 
				"		String responseBody = response.getBody().asString();\r\n" + 
				"		System.out.println(\"Put Response Body is =>  \" + responseBody);\r\n" + 
				"		\r\n" + 
				"	}");
	}

	private void deleteWrite() {
		pr.println("	@Test\r\n" + 
				"	@And(\"I test the api for delete method\")\r\n" + 
				"	public void deleteByIdTest() {\r\n" + 
				"		\r\n" + 
				"		RestAssured.baseURI =  BASEURL + RESTPATH + ID;\r\n" + 
				"		RequestSpecification httpRequest = RestAssured.given();\r\n" + 
				"		Response response = httpRequest.request(Method.DELETE, \"\");\r\n" + 
				"		\r\n" + 
				"		String responseBody = response.getBody().asString();\r\n" + 
				"		System.out.println(\"Delete by id Response Body is =>  \" + responseBody);\r\n" + 
				"	}");
	}

	private void postWrite() {
		
		pr.println("@Test\r\n" + 
				"	@Then(\"I test the generated api calls for post method\")\r\n" + 
				"	public void postTest() throws JSONException {\r\n" + 
				"		\r\n" + 
				"		\r\n" + 
				"		RestAssured.baseURI =  BASEURL + RESTPATH;\r\n" + 
				"		RequestSpecification httpRequest = RestAssured.given();\r\n" + 
				"		\r\n" + 
				"		JSONObject requestParams = new JSONObject();\r\n" + 
				"		\r\n");
		
		for (Column column : table.getColumns()) {
			pr.println("		requestParams.put(\""+ column.getColumnname() +"\", \"\");");
		}
				
    	pr.println(	"		httpRequest.body(requestParams.toString());\r\n" + 
				"		Response response = httpRequest.request(Method.POST);\r\n" + 
				"		\r\n" + 
				"		String responseBody = response.getBody().asString();\r\n" + 
				"		System.out.println(\"Post Response Body is =>  \" + responseBody);\r\n" + 
				"		\r\n" + 
				"	}");
	}

	private void getByIdWrite() {
		pr.println("	@Test\r\n" + 
				"	@When(\"I test the generated api calls for getById method\")\r\n" + 
				"	public void getByIdTest() {\r\n" + 
				"		\r\n" + 
				"		RestAssured.baseURI =  BASEURL + RESTPATH + ID;\r\n" + 
				"		RequestSpecification httpRequest = RestAssured.given();\r\n" + 
				"		Response response = httpRequest.request(Method.GET, \"\");\r\n" + 
				"		\r\n" + 
				"		String responseBody = response.getBody().asString();\r\n" + 
				"		System.out.println(\"GET by id Response Body is =>  \" + responseBody);\r\n" + 
				"	}");
	}

	private void getwrite() {
		pr.println("@Test\r\n" + 
				"	@Given(\"I test the get Api\")\r\n" + 	 
				"	public void getTest() {\r\n" + 
				"		\r\n" + 
				"		RestAssured.baseURI = BASEURL + RESTPATH;\r\n" + 
				"		RequestSpecification httpRequest = RestAssured.given();\r\n" + 
				"		Response response = httpRequest.request(Method.GET,\"\");\r\n" + 
				"		\r\n" + 
				"		String responseBody = response.getBody().asString();\r\n" + 
				"		System.out.println(\"GET Response Body is =>  \" + responseBody);\r\n" + 
				"	}");
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
