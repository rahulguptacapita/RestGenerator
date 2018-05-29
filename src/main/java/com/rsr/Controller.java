package com.rsr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rsr.domain.Request;
import com.rsr.domain.Table;
import com.rsr.service.CodeWriterServiceImpl;

@RestController
public class Controller {

	@Autowired
	TableDaoImpl tableDaoImpl;
	
	@Autowired
	CodeWriterServiceImpl writerServiceImpl;
		
	@PostMapping(path = "/run", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Request> register(@RequestBody final Request request) throws IOException, SQLException {

		request.validateRequest(tableDaoImpl);
		
		request.enrichRequest(tableDaoImpl);
		
		ProjectUtils.copyProjectTemplate(request.getProjectname());
		
		writerServiceImpl.writeCode(request);
			
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/table", produces = "application/json")
	public ResponseEntity<List<String>> register() throws IOException, SQLException {
		
		return new ResponseEntity<List<String>>(tableDaoImpl.getAllTableName(), HttpStatus.OK);
	}

	

}
