package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;
import static com.rsr.ServiceConstants.RSR_DIR;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import com.rsr.domain.Table;;

abstract public class CodeWriter {

	protected PrintWriter pr = null;
	protected String projectName = null;
	protected Table table = null;
	
	public CodeWriter(String projectName) {
		this.projectName = projectName;
		createFileAndInitializePrinter();
	}

	public CodeWriter(Table table, String projectName) {
		this.table = table;
		this.projectName = projectName;
		createFileAndInitializePrinter();
	}

	protected final void blankLine() {
		pr.println(" ");
	}

	protected final void closePrinter() {
		if(pr != null) {
			pr.close();
		}
	}

	public final void createFileAndInitializePrinter() {
		
		try {
			
//			File file = new File(DEST_DIR + projectName + RSR_DIR + getfilePath() + ".java");
			File file = new File(getfilePath());
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			pr = new PrintWriter(file, "cp1252");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	abstract String getClassName();
	
	abstract String getfilePath();

	abstract public void writeClass();

	abstract public void writeCode();

	public void writeEndLine() {
		pr.println("} ");
	}
	
	protected void writeImport() {
		blankLine();
		pr.println("import java.sql.Types;");
		pr.println("import java.sql.Connection;");
		pr.println("import java.sql.DriverManager;");
		pr.println("import java.sql.ResultSet;");
		pr.println("import java.sql.SQLException;");
		pr.println("import java.sql.Statement;");
		pr.println("import org.codehaus.jettison.json.JSONArray;");
		pr.println("import org.codehaus.jettison.json.JSONException;");
		pr.println("import org.codehaus.jettison.json.JSONObject;");
		pr.println("import java.sql.PreparedStatement;");
		pr.println("import java.sql.Types;");
		pr.println("import java.util.Random;");
		pr.println("import java.io.IOException;");
		pr.println("import javax.ws.rs.Consumes;");
		pr.println("import javax.ws.rs.DELETE;");
		pr.println("import javax.ws.rs.GET;");
		pr.println("import javax.ws.rs.POST;");
		pr.println("import javax.ws.rs.PUT;");
		pr.println("import javax.ws.rs.Path;");
		pr.println("import javax.ws.rs.PathParam;");
		pr.println("import javax.ws.rs.Produces;");
		pr.println("import javax.ws.rs.core.MediaType;");
		pr.println("import javax.ws.rs.core.Response;");
		pr.println("import org.codehaus.jackson.JsonGenerationException;");
		pr.println("import org.codehaus.jackson.map.JsonMappingException;");
		pr.println("import org.codehaus.jettison.json.JSONArray;");
		pr.println("import org.codehaus.jettison.json.JSONException;");
		pr.println("import org.codehaus.jettison.json.JSONObject;");
	}

	abstract public void writeMethods();

	abstract public void writepackage();

	abstract public void writeVariables();
	
}
