package com.rsr.service.builddeploy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

abstract public class BuildDeployService implements Runnable {

	private StringBuffer output = new StringBuffer();
	private String buildUrl;
	
	public BuildDeployService(String buildUrl) {
		this.buildUrl = buildUrl;
	}
	
	public String getBuildUrl() {
		return buildUrl;
	}

	public String getOutput() {
		return output.toString();
	}

	protected final void runProcess(String command) throws IOException {

		Process pro = Runtime.getRuntime().exec(command);

		try {
			pro.waitFor();
		} catch (InterruptedException e) {
			throw new RuntimeException("waitFor method thorwn exception " + e.getMessage());
		}

		printLines(" stdout:", pro.getInputStream());
		printLines(" stderr:", pro.getErrorStream());

		int exitValue = pro.exitValue();
		System.out.println(command);
		System.out.println(exitValue);
	}

	protected final void printLines(String cmd, InputStream ins) {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		try {
			while ((line = in.readLine()) != null) {
				String oneLine = cmd + " " + line;
				System.out.println(oneLine);
				output.append(oneLine + "\n");
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
