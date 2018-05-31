package com.rsr.service.builddeploy;

import java.io.File;
import java.io.IOException;

//@Component
// @Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode=
// ScopedProxyMode.TARGET_CLASS)
public class BuildDeployServiceImpl extends BuildDeployServiceExec {

	public BuildDeployServiceImpl(String buildUrl) {
		super(buildUrl);
	}
	
	private void runRunFile() throws IOException {
		
		String s ="\"" + "output" + File.separator + getBuildUrl() + File.separator + "run.bat" + "\"" + " > result.log";
		
		System.out.println(s);
		runProcess(s);
		
		
//		runProcess("run.bat");
		runProcess("netstat -a -n -o");
//		runProcess("cd output");
		//runProcess("mvn clean install");
	}

	@Override
	public void run() {
		try {
			runRunFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
