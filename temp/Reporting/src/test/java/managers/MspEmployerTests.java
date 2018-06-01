package managers;

import org.junit.Test;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class MspEmployerTests {

	private static final String RESTPATH = "mspemployer";
	private static final String BASEURL = "http://localhost:8080/TST129/rest/";
	private static final String ID = "";

//	@Test
//	public void putTest() throws JSONException {
//
//		RestAssured.baseURI = BASEURL + RESTPATH + ID;
//		RequestSpecification httpRequest = RestAssured.given();
//
//		JSONObject requestParams = new JSONObject();
//
//		requestParams.put("NAME", "");
//		requestParams.put("SERIAL_NUMBER", "");
//		requestParams.put("HOUSE_NAME", "");
//		requestParams.put("ADDRESS_1", "");
//		requestParams.put("ADDRESS_2", "");
//		requestParams.put("ADDRESS_3", "");
//		requestParams.put("ADDRESS_4", "");
//		requestParams.put("ADDRESS_5", "");
//		requestParams.put("COUNTY", "");
//		requestParams.put("POSTCODE", "");
//		requestParams.put("COUNTRY_ID", "");
//		requestParams.put("REGION_ID", "");
//		requestParams.put("EMAIL_ADDRESS", "");
//		requestParams.put("WEB_ADDRESS", "");
//		requestParams.put("IN_SCOPE_YN", "");
//		requestParams.put("NAV_CODE_PI", "");
//		requestParams.put("NAV_CODE_SI", "");
//		requestParams.put("WHO_SETUP", "");
//		requestParams.put("DATE_SETUP", "");
//		requestParams.put("WHO_AMENDED", "");
//		requestParams.put("DATE_AMENDED", "");
//		requestParams.put("ACTIVE_DATE", "");
//		requestParams.put("ACTIVE_BY", "");
//		requestParams.put("ACT_YORN", "");
//		requestParams.put("COMPANY_NO", "");
//		requestParams.put("GUID", "");
//		httpRequest.body(requestParams.toString());
//		Response response = httpRequest.request(Method.PUT);
//
//		String responseBody = response.getBody().asString();
//		System.out.println("Put Response Body is =>  " + responseBody);
//
//	}

	@Test
	@Given("I navigate to api url")
	public void getTest() {

//		RequestSpecification httpRequest = RestAssured.given();
//		Response response = httpRequest.request(Method.GET, "");
//
//		String responseBody = response.getBody().asString();
//		System.out.println("GET Response Body is =>  " + responseBody);
//	
	
	
//		RestAssured.baseURI = BASEURL + RESTPATH;
//	
//		Response r = (Response) RestAssured.given().contentType(ContentType.JSON)
//				.get(BASEURL + RESTPATH);
//		
//		JsonPath refJson = r.jsonPath();
//		
//		String actualData = refJson.getString("EMPLOYER_ID");
//		
//		System.out.println("Actual Entity data returned is : " + actualData);
		
		
		
		
		
		/// Assert.assertEquals(response.getStatusCode(), 200);
		// System.out.println(response.asString());
	
	
	
	
	}
//	
//
//	@Test
//	public void postTest() throws JSONException {
//
//		RestAssured.baseURI = BASEURL + RESTPATH;
//		RequestSpecification httpRequest = RestAssured.given();
//
//		JSONObject requestParams = new JSONObject();
//
//		requestParams.put("EMPLOYER_ID", "");
//		requestParams.put("NAME", "");
//		requestParams.put("SERIAL_NUMBER", "");
//		requestParams.put("HOUSE_NAME", "");
//		requestParams.put("ADDRESS_1", "");
//		requestParams.put("ADDRESS_2", "");
//		requestParams.put("ADDRESS_3", "");
//		requestParams.put("ADDRESS_4", "");
//		requestParams.put("ADDRESS_5", "");
//		requestParams.put("COUNTY", "");
//		requestParams.put("POSTCODE", "");
//		requestParams.put("COUNTRY_ID", "");
//		requestParams.put("REGION_ID", "");
//		requestParams.put("EMAIL_ADDRESS", "");
//		requestParams.put("WEB_ADDRESS", "");
//		requestParams.put("IN_SCOPE_YN", "");
//		requestParams.put("NAV_CODE_PI", "");
//		requestParams.put("NAV_CODE_SI", "");
//		requestParams.put("WHO_SETUP", "");
//		requestParams.put("DATE_SETUP", "");
//		requestParams.put("WHO_AMENDED", "");
//		requestParams.put("DATE_AMENDED", "");
//		requestParams.put("ACTIVE_DATE", "");
//		requestParams.put("ACTIVE_BY", "");
//		requestParams.put("ACT_YORN", "");
//		requestParams.put("COMPANY_NO", "");
//		requestParams.put("GUID", "");
//		httpRequest.body(requestParams.toString());
//		Response response = httpRequest.request(Method.POST);
//
//		String responseBody = response.getBody().asString();
//		System.out.println("Post Response Body is =>  " + responseBody);
//
//	}
//
//	@Test
//	public void deleteByIdTest() {
//
//		RestAssured.baseURI = BASEURL + RESTPATH + ID;
//		RequestSpecification httpRequest = RestAssured.given();
//		Response response = httpRequest.request(Method.DELETE, "");
//
//		String responseBody = response.getBody().asString();
//		System.out.println("Delete by id Response Body is =>  " + responseBody);
//	}
//
//	@Test
//	public void getByIdTest() {
//
//		RestAssured.baseURI = BASEURL + RESTPATH + ID;
//		RequestSpecification httpRequest = RestAssured.given();
//		Response response = httpRequest.request(Method.GET, "");
//
//		String responseBody = response.getBody().asString();
//		System.out.println("GET by id Response Body is =>  " + responseBody);
//	}
//
}
