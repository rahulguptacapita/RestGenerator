package com.rsr;

import static com.rsr.ServiceConstants.SRC_DIR_DEV;
import static com.rsr.ServiceConstants.SRC_DIR_TEST;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rsr.domain.Request;
import com.rsr.service.CodeWriterServiceImpl;
import com.rsr.service.builddeploy.BuildDeployServiceExec;
import com.rsr.service.builddeploy.BuildDeployServiceImpl;
@RestController
public class Controller {

	@Autowired
	TableDaoImpl tableDaoImpl;
	
	@Autowired
	CodeWriterServiceImpl writerServiceImpl;
	
	BuildDeployServiceExec buildDeployServiceImpl = null;
		
	@PostMapping(path = "/run", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Request> register(@RequestBody final Request request) throws IOException, SQLException {

		request.validateRequest(tableDaoImpl);
		
		request.enrichRequest(tableDaoImpl);
		
		ProjectUtils.copyProjectTemplate(SRC_DIR_DEV, request.getProjectname());

		ProjectUtils.copyProjectTemplate(SRC_DIR_TEST,request.getTestprojectname());
		
		writerServiceImpl.writeDevCode(request);

		writerServiceImpl.writeTestCode(request);
			
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}
	
	@GetMapping(path = "/builddeploy", produces = "application/json")
	public ResponseEntity<JsonNode> buildAndDeploy(@RequestParam("buildurl") String buildUrl) throws IOException, SQLException {
		
		ObjectMapper mapper = new ObjectMapper();

		JsonNode childNode1 = mapper.createObjectNode();
		((ObjectNode) childNode1).put("message", "hello");
		
		buildDeployServiceImpl = new BuildDeployServiceImpl(buildUrl);
		
		Thread t = new Thread(buildDeployServiceImpl);
		t.start();
		
		return new ResponseEntity<JsonNode>(childNode1, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/output", produces = "application/json")
	public ResponseEntity<JsonNode> getOutput() throws IOException, SQLException {

		if(buildDeployServiceImpl == null) {
			return null; 
		}
		
		ObjectMapper mapper = new ObjectMapper();

		JsonNode childNode1 = mapper.createObjectNode();
		((ObjectNode) childNode1).put("message", buildDeployServiceImpl.getOutput());
		
		return new ResponseEntity<JsonNode>(childNode1, HttpStatus.OK);
	}
	
	@GetMapping(path = "/table", produces = "application/json")
	public ResponseEntity<List<String>> register() throws IOException, SQLException {
		return new ResponseEntity<List<String>>(tableDaoImpl.getAllTableName("TST_%"), HttpStatus.OK);
	}

}
