package com.rsr.service;

import org.springframework.stereotype.Component;
import com.rsr.domain.Request;
import com.rsr.domain.Table;

@Component
public class CodeWriterServiceImpl {

	public void writeDevCode(Request request) {
		
		for (Table table : request.getTables()) {
			CodeWriter columnCodeWriter = new ColumnCodeWriter(table, request.getProjectname());
			columnCodeWriter.writeCode();
			
			CodeWriter restCodeWriter = new RestCodeWriter(table, request.getProjectname());
			restCodeWriter.writeCode();
			
			CodeWriter dbRestCodeWriter = new DBRestCodeWriter(table, request.getProjectname());
			dbRestCodeWriter.writeCode();
			
			CodeWriter jUnitCodeWriter = new JunitCodeWriter(table, request.getProjectname());
			jUnitCodeWriter.writeCode();
			
		}
		
		CodeWriter dbEntityWriter = new DBEntityWriter(request.getProjectname());
		dbEntityWriter.writeCode();

		CodeWriter pomWriter = new POMCodeWriterWithSwagger(request.getProjectname());
		pomWriter.writeCode();

		CodeWriter runMeWriter = new RunMeCodeWriter(request.getProjectname(), request.getProjectPath());
		runMeWriter.writeCode();
	}

	public void writeTestCode(Request request) {
		
		String featureFileName = "";
		
		for (Table table : request.getTables()) {
		
			CodeWriter stepDefinationCodeWriter = new StepDefinationCodeWriter(table, request.getTestprojectname());
			stepDefinationCodeWriter.writeCode();
			
			CodeWriter featureCodeWriter = new FeatureCodeWriter(table, request.getTestprojectname());
			featureCodeWriter.writeCode();
			
			featureFileName = featureCodeWriter.getClassName() + ".feature";
			
			
			CodeWriter testPomCodeWriter = new TestPOMCodeWriter(request.getTestprojectname());
			testPomCodeWriter.writeCode();
			
		}
	
		CodeWriter cukesCodeWriter = new CukesCodeWriter(featureFileName, request.getTestprojectname());
		cukesCodeWriter.writeCode();
	
	}
}
