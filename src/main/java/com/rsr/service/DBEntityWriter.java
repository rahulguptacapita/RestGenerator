package com.rsr.service;

import static com.rsr.ServiceConstants.ENTITY;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component
@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode= ScopedProxyMode.TARGET_CLASS)
public class DBEntityWriter  extends CodeWriter{

	public DBEntityWriter(String projectName) {
		super(projectName);
	}

	@Override
	String getfilePath() {
		return  ENTITY + "\\" + getClassName();
	}
	
	@Override
	String getClassName() {
		return "DBEntity";
	}

	@Override
	public void writepackage() {}

	@Override
	public void writeClass() {}

	@Override
	public void writeMethods() {}

	@Override
	public void writeVariables() {}

	@Override
	public void writeCode() {
		pr.println("package com.rsr.entity;\r\n" + 
				"\r\n" + 
				"import java.sql.Connection;\r\n" + 
				"import java.sql.DriverManager;\r\n" + 
				"import java.sql.ResultSet;\r\n" + 
				"import java.sql.SQLException;\r\n" + 
				"import java.sql.Statement;\r\n" + 
				"import org.codehaus.jettison.json.JSONArray;\r\n" + 
				"import org.codehaus.jettison.json.JSONException;\r\n" + 
				"import org.codehaus.jettison.json.JSONObject;\r\n" + 
				"\r\n" + 
				"abstract public class DBEntity {\r\n" + 
				"\r\n" + 
				"    public enum DatabaseType { ORACLE, SQLSERVER }\r\n" + 
				"   \r\n" + 
				"    private String host = \"localhost\";\r\n" + 
				"    private String port = \"1433\";  //\r\n" + 
				"    private String sid = \"DEV\";   // SID\r\n" + 
				"    private String username = \"dev\"; // DATABASE USERNAME\r\n" + 
				"    private String password = \"Integra100\";  // DATABASE PASSWORD\r\n" + 
				"    public DatabaseType databseType = DatabaseType.SQLSERVER;\r\n" + 
				"\r\n" + 
				"    \r\n" + 
				"    public String getHost() {\r\n" + 
				"        return host;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    public void setHost(String host) {\r\n" + 
				"        this.host = host;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    public String getPort() {\r\n" + 
				"        return port;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    \r\n" + 
				"    public void setPort(String port) {\r\n" + 
				"        this.port = port;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    \r\n" + 
				"    public String getSid() {\r\n" + 
				"        return sid;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    \r\n" + 
				"    public void setSid(String sid) {\r\n" + 
				"        this.sid = sid;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    \r\n" + 
				"    public String getUsername() {\r\n" + 
				"        return username;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    public void setUsername(String username) {\r\n" + 
				"        this.username = username;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    public String getPassword() {\r\n" + 
				"        return password;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"\r\n" + 
				"    public void setPassword(String password) {\r\n" + 
				"        this.password = password;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    \r\n" + 
				"    public DatabaseType getDatabseType() {\r\n" + 
				"        return databseType;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    public void setDatabseType(DatabaseType databseType) {\r\n" + 
				"        this.databseType = databseType;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    public  Connection getConnection() {\r\n" + 
				"\r\n" + 
				"        Connection conn = null;\r\n" + 
				"\r\n" + 
				"        try {\r\n" + 
				"            switch (getDatabseType()) {\r\n" + 
				"            case ORACLE:\r\n" + 
				"                Class.forName(\"oracle.jdbc.driver.OracleDriver\");\r\n" + 
				"                break;\r\n" + 
				"            case SQLSERVER:\r\n" + 
				"                Class.forName(\"com.microsoft.sqlserver.jdbc.SQLServerDriver\");\r\n" + 
				"                Class.forName(\"net.sourceforge.jtds.jdbc.Driver\");\r\n" + 
				"                break;\r\n" + 
				"            }\r\n" + 
				"            conn = DriverManager.getConnection(buildURL(), getUsername(), getPassword());\r\n" + 
				"\r\n" + 
				"        } catch (ClassNotFoundException e) {\r\n" + 
				"            throw new RuntimeException(e.getMessage(), e);\r\n" + 
				"        } catch (SQLException e) {\r\n" + 
				"            throw new RuntimeException(e.getMessage(), e);\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        return conn;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    private String buildURL() {\r\n" + 
				"\r\n" + 
				"        String oracleURL = \"jdbc:oracle:thin:@<HOST>:<PORT>:<SID>\";\r\n" + 
				"        String sqlURL = \"jdbc:sqlserver://<HOST>:<PORT>;database=<SID>\";\r\n" + 
				"\r\n" + 
				"        String replacedURL = null;\r\n" + 
				"\r\n" + 
				"        switch (getDatabseType()) {\r\n" + 
				"\r\n" + 
				"        case ORACLE:\r\n" + 
				"            replacedURL = oracleURL;\r\n" + 
				"            break;\r\n" + 
				"        case SQLSERVER:\r\n" + 
				"            replacedURL = sqlURL;\r\n" + 
				"            break;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        replacedURL = replacedURL.replaceFirst(\"<HOST>\", getHost());\r\n" + 
				"        replacedURL = replacedURL.replaceFirst(\"<PORT>\", getPort());\r\n" + 
				"        replacedURL = replacedURL.replaceFirst(\"<SID>\", getSid());\r\n" + 
				"\r\n" + 
				"        return replacedURL;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    protected void closeConn(Connection conn, Statement stat, ResultSet rs) {\r\n" + 
				"    	if (rs != null) {\r\n" + 
				"		    try { rs.close(); } catch (Exception e) { /**/ }\r\n" + 
				"		}\r\n" + 
				"		if (stat != null) {\r\n" + 
				"		    try { stat.close(); } catch (Exception e) { /**/ }\r\n" + 
				"		}\r\n" + 
				"		if (conn != null) {\r\n" + 
				"		    try { conn.close(); } catch (Exception e) { /**/ }\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"    \r\n" + 
				"    public void validateEntity(JSONObject entity, String primaryKey) throws JSONException {\r\n" + 
				"    	\r\n" + 
				"    	for (TstClaimHeaderEntityColumn tstClaimHeaderEntityColumn : TstClaimHeaderEntityColumn.values()) {\r\n" + 
				"    		if(primaryKey.equalsIgnoreCase(tstClaimHeaderEntityColumn.name())) {\r\n" + 
				"    			continue;\r\n" + 
				"    		}\r\n" + 
				"    		Object columnValue = entity.get(tstClaimHeaderEntityColumn.name());\r\n" + 
				"    		if(columnValue == null) {\r\n" + 
				"    			throw new RuntimeException(\"cannot find \" + tstClaimHeaderEntityColumn.name());\r\n" + 
				"    		}\r\n" + 
				"    	}\r\n" + 
				"    	if(TstClaimHeaderEntityColumn.values().length != entity.length() && TstClaimHeaderEntityColumn.values().length - 1 == entity.length()) {\r\n" + 
				"    		throw new RuntimeException(\"More then enough parameter found \");\r\n" + 
				"    	}\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    public abstract JSONArray getEntities(String tableName) throws JSONException;\r\n" + 
				"	public abstract void postEntity(String tableName, String primaryKey, JSONObject entity) throws JSONException;\r\n" + 
				"	public abstract JSONObject deleteEntity(String tableName, String id) throws JSONException;\r\n" + 
				"	public abstract JSONObject getEntity(String tableName, String id) throws JSONException;	\r\n" + 
				"	\r\n" + 
				"}\r\n" + 
				"");
		closePrinter();
	}
}
