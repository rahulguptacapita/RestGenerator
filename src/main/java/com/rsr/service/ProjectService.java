package com.rsr.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

			
	//		printLines(" stdout:", process.getInputStream());
	//		printLines(" stderr:", process.getErrorStream());

			int exitValue = process.exitValue();
		
			System.out.println(exitValue);
			
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

	
	protected final void printLines(String cmd, InputStream ins) {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		try {
			while ((line = in.readLine()) != null) {
				String oneLine = cmd + " " + line;
				System.out.println(oneLine);
			//	output.append(oneLine + "\n");
			}
		} catch (IOException e) {
		} finally {
			try {
				ins.close();
			} catch (IOException e) {
			}
			try {
				in.close();
			} catch (IOException e) {
			}
		}
	}
	
	
}
