package com.rsr.service;

import java.io.File;
import java.io.IOException;

public class ProjectService {

	private final String CURRENT_DIR = System.getProperty("user.dir") + File.separator;
	private String DESTINATION_PATH;
	private String PROJECT_NAME;

	public ProjectService(String destDir, String projectName) {
		this.DESTINATION_PATH = destDir;
		this.PROJECT_NAME = projectName;
	}

	public int initiateProject() {

		Process process = null;

		String path = buildExecPath(DESTINATION_PATH, PROJECT_NAME);

		try {

			process = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd " + path + " && run.bat\"");
			process.waitFor();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return process.exitValue();
	}

	private String buildExecPath(String destinationPath, String projectName) {
		return CURRENT_DIR + destinationPath + projectName;
	}

}
