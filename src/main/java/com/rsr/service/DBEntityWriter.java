package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;
import static com.rsr.ServiceConstants.ENTITY;
import static com.rsr.ServiceConstants.RSR_DIR;

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
		return DEST_DIR + projectName + RSR_DIR + ENTITY + "\\" + getClassName() + ".java";
		//return  ENTITY + "\\" + getClassName();
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
				"    public abstract void validateEntity(JSONObject entity, String primaryKey) throws JSONException;\r\n" + 
				"    \r\n" + 
				"    public abstract JSONArray getEntities(String tableName) throws JSONException;\r\n" + 
				"	public abstract void postEntity(String tableName, String primaryKey, JSONObject entity) throws JSONException;\r\n" + 
				"	public abstract JSONObject deleteEntity(String tableName, String id) throws JSONException;\r\n" + 
				"	public abstract JSONObject getEntity(String tableName, String id) throws JSONException;\r\n" + 
				"\r\n" + 
				"	public abstract void putEntity(String tableName, String primaryKey, JSONObject entity, String id) throws JSONException;\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"	public static int getNextNo(Connection conn, String tableName, String columnName, String columnCaption) {\r\n" + 
				"\r\n" + 
				"        String lastNoAsString = getLastNo(conn, tableName, columnName);\r\n" + 
				"\r\n" + 
				"        int lastNoInDb = 0;\r\n" + 
				"        if (lastNoAsString == null) {\r\n" + 
				"            addLastNoSetting(conn, tableName, columnName, columnCaption);\r\n" + 
				"        } else if (lastNoAsString.length() > 0) {\r\n" + 
				"            lastNoInDb = Integer.parseInt(lastNoAsString);\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        for (int i = 1; i <= 100; i++) {\r\n" + 
				"\r\n" + 
				"            int nextNo = lastNoInDb + 1;\r\n" + 
				"\r\n" + 
				"            if (noHasBeenUsed(conn, tableName, columnName, nextNo)) {\r\n" + 
				"                nextNo = getMaxNo(conn, tableName, columnName) + 1;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            if (setLastNo(conn, tableName, columnName, lastNoInDb, nextNo)) {\r\n" + 
				"                return nextNo;\r\n" + 
				"    		} else {\r\n" + 
				"    			lastNoInDb = Integer.parseInt(getLastNo(conn, tableName, columnName));\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"        }\r\n" + 
				"        throw new RuntimeException(\"Failed to get next number after 100 attempts\");\r\n" + 
				"    }\r\n" + 
				"	\r\n" + 
				"	   private static boolean setLastNo(Connection conn, String tableName, String columnName, int lastNo, int nextNo) {\r\n" + 
				"\r\n" + 
				"	        Statement stat = null;\r\n" + 
				"	        try {\r\n" + 
				"	            String sql = \"update cau_settings set setting_value = '\" + nextNo + \"' where section = 'LAST_USED_NOS' and setting = '\"\r\n" + 
				"	                    + tableName + \".\" + columnName + \"' and setting_value = '\" + lastNo + \"'\";\r\n" + 
				"	            stat = conn.createStatement();\r\n" + 
				"	            if (stat.executeUpdate(sql) == 1) {\r\n" + 
				"	                return true;\r\n" + 
				"	            }\r\n" + 
				"	        } catch (SQLException e) {\r\n" + 
				"	            throw new RuntimeException(e.getMessage(), e);\r\n" + 
				"	        } finally {\r\n" + 
				"	        }\r\n" + 
				"	        return false;\r\n" + 
				"	    }\r\n" + 
				"	\r\n" + 
				"    private static int getMaxNo(Connection conn, String tableName, String columnName) {\r\n" + 
				"\r\n" + 
				"        Statement stat = null;\r\n" + 
				"        ResultSet rs = null;\r\n" + 
				"        try {\r\n" + 
				"            stat = conn.createStatement();\r\n" + 
				"            rs = stat.executeQuery(\"select max(\" + columnName + \") from \" + tableName);\r\n" + 
				"            rs.next();\r\n" + 
				"            return rs.getInt(1);\r\n" + 
				"        } catch (SQLException e) {\r\n" + 
				"            throw new RuntimeException(e.getMessage(), e);\r\n" + 
				"        } finally {\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    private static boolean noHasBeenUsed(Connection conn, String tableName, String columnName, int nextNo) {\r\n" + 
				"        boolean noHasBeenUsed = false;\r\n" + 
				"        Statement stat = null;\r\n" + 
				"        ResultSet rs = null;\r\n" + 
				"        try {\r\n" + 
				"            String sql = \"select count(*) from \" + tableName + \" where \" + columnName + \" = \" + nextNo;\r\n" + 
				"            stat = conn.createStatement();\r\n" + 
				"            rs = stat.executeQuery(sql);\r\n" + 
				"            rs.next();\r\n" + 
				"            if (rs.getInt(1) > 0) {\r\n" + 
				"                noHasBeenUsed = true;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"        } catch (SQLException e) {\r\n" + 
				"            throw new RuntimeException(e.getMessage(), e);\r\n" + 
				"        } finally {\r\n" + 
				"        }\r\n" + 
				"        return noHasBeenUsed;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    private static void addLastNoSetting(Connection conn, String tableName, String columnName, String columnCaption) {\r\n" + 
				"        Statement stat = null;\r\n" + 
				"\r\n" + 
				"        try {\r\n" + 
				"            String sql = \"insert into cau_settings (section, setting, setting_seq, setting_value, setting_prompts, setting_descr, setting_format, application_id) \"\r\n" + 
				"                    + \"values ('LAST_USED_NOS','\"\r\n" + 
				"                    + tableName\r\n" + 
				"                    + \".\"\r\n" + 
				"                    + columnName\r\n" + 
				"                    + \"',0,'0','\"\r\n" + 
				"                    + columnCaption\r\n" + 
				"                    + \"','Last Used Number for \"\r\n" + 
				"                    + columnCaption + \"','', '\" + tableName.substring(0, 3) + \"')\";\r\n" + 
				"            stat = conn.createStatement();\r\n" + 
				"            stat.executeUpdate(sql);\r\n" + 
				"        } catch (SQLException e) {\r\n" + 
				"            throw new RuntimeException(e.getMessage(), e);\r\n" + 
				"        } finally {\r\n" + 
				"        	//closeConn(Connection conn, Statement stat, ResultSet rs)\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"	\r\n" + 
				"	   private static String getLastNo(Connection conn, String tableName, String columnName) {\r\n" + 
				"	        String lastNo = null;\r\n" + 
				"	        Statement stat = null;\r\n" + 
				"	        ResultSet rs = null;\r\n" + 
				"	        try {\r\n" + 
				"	            String sql = \"select setting_value from cau_settings where section = 'LAST_USED_NOS' and setting = '\" + tableName + \".\"\r\n" + 
				"	                    + columnName + \"'\";\r\n" + 
				"	            stat = conn.createStatement();\r\n" + 
				"	            rs = stat.executeQuery(sql);\r\n" + 
				"	            if (rs.next()) {\r\n" + 
				"	                lastNo = rs.getString(\"setting_value\");\r\n" + 
				"	                if (lastNo == null) {\r\n" + 
				"	                    lastNo = \"\";\r\n" + 
				"	                }\r\n" + 
				"	            }\r\n" + 
				"	        } catch (SQLException e) {\r\n" + 
				"	            throw new RuntimeException(e.getMessage(), e);\r\n" + 
				"	        } finally {\r\n" + 
				"	        }\r\n" + 
				"	        return lastNo;\r\n" + 
				"	    }\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"}\r\n" + 
				"");
		closePrinter();
		
		// table.getTableClassName() + "Entity"
	}
}
