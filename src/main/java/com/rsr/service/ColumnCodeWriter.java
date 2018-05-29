package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;
import static com.rsr.ServiceConstants.ENTITY;
import static com.rsr.ServiceConstants.ENTITY_COLUMN;
import static com.rsr.ServiceConstants.RSR_DIR;

import java.io.PrintWriter;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.rsr.domain.Column;
import com.rsr.domain.Table;

@Component
@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode= ScopedProxyMode.TARGET_CLASS)
public class ColumnCodeWriter extends CodeWriter {

	
	public ColumnCodeWriter(Table table, String projectName) {
		super(table, projectName);
	}

	@Override
	String getfilePath() {
		return DEST_DIR + projectName + RSR_DIR + ENTITY + "\\" + getClassName() + ".java";
		
		//return  ENTITY + "\\" + getClassName();
	}
	
	@Override
	String getClassName() {
		return table.getTableClassName() + ENTITY_COLUMN;
	}

	@Override
	protected void writeImport() {
		pr.println("import java.sql.Types;");
	}


	@Override
	public void writepackage() {
		pr.println("package com.rsr.entity;");		
	}

	@Override
	public void writeClass() {
		pr.println("public enum "+ getClassName() +" {");		
	}


	@Override
	public void writeVariables() {
		
		for(Column column : table.getColumns()) {
			pr.println("     " + column.getColumnname() +" (" + column.getDatatype() + "),");		
		}
		pr.println(";");		
		
		pr.println("  private int columnType;");
	}

	@Override
	public void writeMethods() {
		
		pr.println(" "+ getClassName() +"(int columnType) {");
		pr.println("  	this.columnType = columnType;");	
		pr.println(" }");
		
		pr.println("  public int getColumnType() {");
	    pr.println("  	return columnType;");
	    pr.println(" }");
		
	}
	
	@Override
	public void writeCode() {
		writepackage();
		writeImport();
		writeClass();
		writeVariables();
		writeMethods();
		writeEndLine();
		closePrinter();
	}
	
}
