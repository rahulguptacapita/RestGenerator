package com.rsr.service.builddeploy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

abstract public class BuildDeployServiceExec implements Runnable {

	private StringBuffer output = new StringBuffer();
	private String buildUrl;

	public BuildDeployServiceExec(String buildUrl) {
		this.buildUrl = buildUrl;
	}

	public String getBuildUrl() {
		return buildUrl;
	}

	public String getOutput() {
		return output.toString();
	}

	protected final void runProcess(String command) throws IOException {
		System.out.print("executing  code ");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		CommandLine commandline = CommandLine.parse(command);
		DefaultExecutor exec = new DefaultExecutor();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		exec.setStreamHandler(streamHandler);
		
		int exitValue = exec.execute(commandline);
		output.append(outputStream.toString());

		System.out.println("output stream is this " + outputStream.toString());
		System.out.println("output stream is this " + output);

		
		// Map<String, File> map = new HashMap<>();

		// map.put("file", new File("invoice.pdf"));
		// CommandLine cmdLine = new CommandLine(command);
		// cmdLine.setSubstitutionMap(map);
		// DefaultExecutor executor = new DefaultExecutor();
		// executor.setExitValue(1);
		// ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
		// executor.setWatchdog(watchdog);
		// int exitValue = executor.execute(cmdLine);

	}
}
