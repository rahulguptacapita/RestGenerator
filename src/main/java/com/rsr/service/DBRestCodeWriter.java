package com.rsr.service;

import static com.rsr.ServiceConstants.DEST_DIR;
import static com.rsr.ServiceConstants.ENTITY;
import static com.rsr.ServiceConstants.RSR_DIR;

import com.rsr.domain.Method;
import com.rsr.domain.Table;

public class DBRestCodeWriter extends CodeWriter{

	private static final String GET = null;

	public DBRestCodeWriter(Table table, String projectName) {
		super(table, projectName);
	}

	@Override
	String getfilePath() {
		return DEST_DIR + projectName + RSR_DIR + ENTITY + "\\" + getClassName() + ".java";
		//return ENTITY + "\\" + getClassName();
	}

	@Override
	String getClassName() {
		return table.getTableClassName() + "Entity";
	}
	
	@Override
	public void writepackage() {
		pr.println("package com.rsr.entity;");
	}

	@Override
	public void writeClass() {
		pr.println("public class "+ getClassName() + " extends DBEntity {");
	}

	

	@Override
	public void writeMethods() {
		
		writeGetObjectForColumn();
		
		writePrepareQuery();
		
		writePrepareUpdateQuery();
		
		writeRandomPrimaryKeyFunction();
		
		writevalidateEntity();
		
		
		for (Method req : table.getRequests()) {
			switch (req) {
			case GET:
				getWrite();
				break;
			case POST:
				postWrite();
				break;
			case GETBYID:
				getByIdWrite();
				break;
			case DELETE:
				deleteWrite();
				break;
			case PUT:
				putWrite();
				break;
			default:
				break;
			}
		}
		
		for(Method m : Method.values()) {
			if(!table.getRequests().contains(m)) {
				switch (m) {
				case GET:
					getWriteOverride();
					break;
				case POST:
					postWriteOverride();
					break;
				case GETBYID:
					getByIdWriteOverride();
					break;
				case DELETE:
					deleteWriteOverride();
					break;
				case PUT:
					putWriteOverride();
					break;
				default:
					break;
				}
			}
		}
	}
	
	private void writevalidateEntity() {
		pr.println("public void validateEntity(JSONObject entity, String primaryKey) throws JSONException {\r\n" + 
				"\r\n" + 
				"		for ("+getClassName()+"Column column : "+getClassName()+"Column.values()) {\r\n" + 
				"			if (primaryKey.equalsIgnoreCase(column.name())) {\r\n" + 
				"				continue;\r\n" + 
				"			}\r\n" + 
				"			Object columnValue = entity.get(column.name());\r\n" + 
				"			if (columnValue == null) {\r\n" + 
				"				throw new RuntimeException(\"cannot find \" + column.name());\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"		if ("+getClassName()+"Column.values().length != entity.length()\r\n" + 
				"				&& "+getClassName()+"Column.values().length - 1 == entity.length()) {\r\n" + 
				"			throw new RuntimeException(\"More then enough parameter found \");\r\n" + 
				"		}\r\n" + 
				"	}");
	}

	private void putWriteOverride() {
		pr.println("@Override\r\n" + 
				"	public void putEntity(String tableName, String primaryKey, JSONObject entity, String id) throws JSONException {\r\n" + 
				"		// TODO Auto-generated method stub\r\n" + 
				"		\r\n" + 
				"	}");
	}

	private void writePrepareUpdateQuery() {
		pr.println("	private String prepareUpdateQuery(String tableName, String primaryKey, JSONObject entity, String id) throws JSONException {\r\n" + 
				"		\r\n" + 
				"		String sql = \"\";\r\n" + 
				"		\r\n" + 
				"		for ("+ getClassName() +"Column entityColumn : "+ getClassName() +"Column.values()) {\r\n" + 
				"			if(entityColumn.name().equalsIgnoreCase(primaryKey)) {\r\n" + 
				"				continue;\r\n" + 
				"			}\r\n" + 
				"			sql = sql + entityColumn.name() + \" = \" + \"?,\";\r\n" + 
				"		}\r\n" + 
				"		sql = sql.substring(0, sql.length() - 1);\r\n" + 
				"		\r\n" + 
				"		\r\n" + 
				"		String whereClause = \" where \" + primaryKey + \" = \" + \"?\";\r\n" + 
				"		String updateQuery = \"UPDATE \" + tableName + \" SET \" + sql + whereClause;\r\n" + 
				"		return updateQuery;\r\n" + 
				"	}");
	}

	private void putWrite() {
		pr.println("@Override\r\n" + 
				"	public void putEntity(String tableName, String primaryKey, JSONObject entity, String id) throws JSONException {\r\n" + 
				"		\r\n" + 
				"		Connection conn = null;\r\n" + 
				"		Statement stat = null;\r\n" + 
				"		ResultSet rs = null;\r\n" + 
				"\r\n" + 
				"		try {\r\n" + 
				"			conn = getConnection();\r\n" + 
				"			entity.remove(primaryKey);\r\n" + 
				"			entity.put(primaryKey, id);\r\n" + 
				"			\r\n" + 
				"			String sql = prepareUpdateQuery(tableName, primaryKey, entity, id);\r\n" + 
				"			System.out.println(sql);\r\n" + 
				"			\r\n" + 
				"			PreparedStatement pstmt = conn.prepareStatement(sql);\r\n" + 
				"			 int index = 1;\r\n" + 
				"\r\n" + 
				"			 for ("+ getClassName() +"Column entityColumn : "+ getClassName() +"Column.values()) {\r\n" + 
				"				 if(entityColumn.name().equalsIgnoreCase(primaryKey)) {\r\n" + 
				"						continue;\r\n" + 
				"				 }\r\n" + 
				"				 pstmt.setObject(index, entity.get(entityColumn.name()));\r\n" + 
				"				 index++;\r\n" + 
				"			 }\r\n" + 
				"			 \r\n" + 
				"			pstmt.setObject(index, id);\r\n" + 
				"			pstmt.execute();\r\n" + 
				"\r\n" + 
				"		} catch (SQLException sqle) {\r\n" + 
				"			throw new RuntimeException(sqle.getMessage(), sqle);\r\n" + 
				"		} finally {\r\n" + 
				"			closeConn(conn, stat, rs);\r\n" + 
				"		}\r\n" + 
				"	}");
	}

	private void deleteWriteOverride() {
		pr.println("@Override\r\n" + 
				"	public JSONObject deleteEntity(String tableName, String id) throws JSONException {\r\n" + 
				"		// TODO Auto-generated method stub\r\n" + 
				"		return null;\r\n" + 
				"	}");
	}

	private void getByIdWriteOverride() {
		pr.println("@Override\r\n" + 
				"	public JSONObject getEntity(String tableName, String id) throws JSONException {\r\n" + 
				"		// TODO Auto-generated method stub\r\n" + 
				"		return null;\r\n" + 
				"	}");
	}

	private void postWriteOverride() {
		pr.println("@Override\r\n" + 
				"	public void postEntity(String tableName, String primaryKey, JSONObject entity) throws JSONException {\r\n" + 
				"		// TODO Auto-generated method stub\r\n" + 
				"		\r\n" + 
				"	}");
	}

	private void getWriteOverride() {
		pr.println("@Override\r\n" + 
				"	public JSONArray getEntities(String tableName) throws JSONException {\r\n" + 
				"		// TODO Auto-generated method stub\r\n" + 
				"		return null;\r\n" + 
				"	}");
	}

	private void writeRandomPrimaryKeyFunction() {
		pr.println("private String getRandomPrimaryKey() {\r\n" + 
				"		Random rnd = new Random();\r\n" + 
				"		String n = 100000 + rnd.nextInt(900000) + \"\";\r\n" + 
				"		return n;\r\n" + 
				"	}\r\n" + 
				"");
	}

	private void writePrepareQuery() {
		pr.println("	private String prepareQuery(String tableName, String primaryKey, JSONObject entity)\r\n" + 
				"			throws SQLException, JSONException {\r\n" + 
				"		\r\n" + 
				"		String sql;\r\n" + 
				"		String columns = \"(\";\r\n" + 
				"		String values = \"(\";\r\n" + 
				"\r\n" + 
				"		for ("+ getClassName() +"Column entityColumn : "+ getClassName() +"Column.values()) {\r\n" + 
				"   			columns = columns + entityColumn.name();\r\n" + 
				"	    	 values = values + \"?\";\r\n" + 
				"			columns = columns + \",\";\r\n" + 
				"			values = values + \",\";\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"		columns = columns.substring(0, columns.length() - 1);\r\n" + 
				"		values = values.substring(0, values.length() - 1);\r\n" + 
				"\r\n" + 
				"		columns = columns + \")\";\r\n" + 
				"		values = values + \" )\";\r\n" + 
				"\r\n" + 
				"		sql = \"INSERT INTO \" + tableName + columns + \" VALUES\" + values;\r\n" + 
				"		return sql;\r\n" + 
				"	}");
	}

	private void writeGetObjectForColumn() {
		pr.println("private Object getObjectForColumn(ResultSet rs, "+ getClassName() +"Column entityColumn) throws SQLException {\r\n" + 
				"\r\n" + 
				"		int columnType = entityColumn.getColumnType();\r\n" + 
				"		switch (columnType) {\r\n" + 
				"		case Types.VARCHAR:\r\n" + 
				"		case Types.LONGNVARCHAR:\r\n" + 
				"		case Types.CHAR:\r\n" + 
				"		case Types.NVARCHAR:\r\n" + 
				"		case Types.NCHAR:\r\n" + 
				"			return rs.getString(entityColumn.name());\r\n" + 
				"		case Types.DATE:\r\n" + 
				"		case Types.TIME:\r\n" + 
				"		case Types.TIMESTAMP:\r\n" + 
				"			return rs.getDate(entityColumn.name());\r\n" + 
				"		case Types.INTEGER:\r\n" + 
				"		case Types.BIGINT:\r\n" + 
				"		case Types.SMALLINT:\r\n" + 
				"		case Types.TINYINT:\r\n" + 
				"		case Types.NUMERIC:\r\n" + 
				"			return rs.getInt(entityColumn.name());\r\n" + 
				"		case Types.DOUBLE:\r\n" + 
				"			return rs.getDouble(entityColumn.name());\r\n" + 
				"		case Types.FLOAT:\r\n" + 
				"			return rs.getFloat(entityColumn.name());\r\n" + 
				"		case Types.BLOB:\r\n" + 
				"			return rs.getBlob(entityColumn.name());\r\n" + 
				"		default:\r\n" + 
				"			return rs.getObject(entityColumn.name());\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"");
	}


	private void postWrite() {
		pr.println("@Override\r\n" + 
				"	public void postEntity(String tableName, String primaryKey, JSONObject entity) throws JSONException {\r\n" + 
				"\r\n" + 
				"		Connection conn = null;\r\n" + 
				"		Statement stat = null;\r\n" + 
				"		ResultSet rs = null;\r\n" + 
				"\r\n" + 
				"		try {\r\n" + 
				"			conn = getConnection();\r\n" + 
				"			entity.remove(primaryKey);\r\n" + 
				"			entity.put(primaryKey, getRandomPrimaryKey());\r\n" + 
				"			String sql = prepareQuery(tableName, primaryKey, entity);\r\n" + 
				"			System.out.println(sql);\r\n" + 
				"			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);\r\n" + 
				"			\r\n" + 
				"			 int index = 1;\r\n" + 
				"			 for ("+ getClassName()+"Column entityColumn : "+ getClassName() +"Column.values()) {\r\n" + 
				"			 	 pstmt.setObject(index,  entity.get(entityColumn.name()));\r\n" + 
				"				 index++;\r\n" + 
				"			 }\r\n" + 
				"		 \r\n" + 
				"			pstmt.execute();\r\n" + 
				"			System.out.println(sql);\r\n" + 
				"\r\n" + 
				"		} catch (SQLException sqle) {\r\n" + 
				"			throw new RuntimeException(sqle.getMessage(), sqle);\r\n" + 
				"		} finally {\r\n" + 
				"			closeConn(conn, stat, rs);\r\n" + 
				"		}\r\n" + 
				"	}");
	}

	private void getWrite() {
		pr.println("	public JSONArray getEntities(String tableName) throws JSONException {\r\n" + 
				"		Connection conn = null;\r\n" + 
				"		Statement stat = null;\r\n" + 
				"		ResultSet rs = null;\r\n" + 
				"\r\n" + 
				"		JSONArray array = new JSONArray();\r\n" + 
				"		try {\r\n" + 
				"			conn = getConnection();\r\n" + 
				"			stat = conn.createStatement();\r\n" + 
				"			rs = stat.executeQuery(\"select * from \" + tableName);\r\n" + 
				"\r\n" + 
				"			while (rs.next()) {\r\n" + 
				"				JSONObject object = new JSONObject();\r\n" + 
				"				for ("+ getClassName()+"Column entityColumn : "+ getClassName() +"Column.values()) {\r\n" + 
				"					if (rs.getObject(entityColumn.name()) != null) {\r\n" + 
				"						object.put(entityColumn.name(), getObjectForColumn(rs, entityColumn));\r\n" + 
				"					} else {\r\n" + 
				"						object.put(entityColumn.name(), \"\");\r\n" + 
				"					}\r\n" + 
				"				}\r\n" + 
				"				array.put(object);\r\n" + 
				"			}\r\n" + 
				"\r\n" + 
				"		} catch (SQLException sqle) {\r\n" + 
				"			throw new RuntimeException(sqle.getMessage(), sqle);\r\n" + 
				"		} finally {\r\n" + 
				"			closeConn(conn, stat, rs);\r\n" + 
				"		}\r\n" + 
				"		return array;\r\n" + 
				"	}");
	}

	private void getByIdWrite() {
		pr.println("	@Override\r\n" + 
				"	public JSONObject deleteEntity(String tableName, String id) throws JSONException {\r\n" + 
				"		\r\n" + 
				"		Connection conn = null;\r\n" + 
				"		Statement stat = null;\r\n" + 
				"		ResultSet rs = null;\r\n" + 
				"\r\n" + 
				"		JSONObject result = new JSONObject();\r\n" + 
				"\r\n" + 
				"		try {\r\n" + 
				"			conn = getConnection();\r\n" + 
				"			stat = conn.createStatement();\r\n" + 
				"			String query = \"delete from  \" + tableName + \" where "+ table.getPrimaryKey() +" like ?\";\r\n" + 
				"		    PreparedStatement pstmt = conn.prepareStatement(query);\r\n" + 
				"		    pstmt.setString(1, id);\r\n" + 
				"		    int executeUpdate = pstmt.executeUpdate();\r\n" + 
				"		      \r\n" + 
				"		    if(executeUpdate == 0 ) {\r\n" + 
				"		      result.put(\"message\", \"No record found\");\r\n" + 
				"		      return result;\r\n" + 
				"		    }\r\n" + 
				"		      \r\n" + 
				"		} catch (SQLException sqle) {\r\n" + 
				"			throw new RuntimeException(sqle.getMessage(), sqle);\r\n" + 
				"		} finally {\r\n" + 
				"			closeConn(conn, stat, rs);\r\n" + 
				"		}\r\n" + 
				"		result.put(\"message\", \"deleted successfully\");\r\n" + 
				"		return result;\r\n" + 
				"	}");
	}

	private void deleteWrite() {
		pr.println("@Override\r\n" + 
				"	public JSONObject getEntity(String tableName, String id) throws JSONException {\r\n" + 
				"		\r\n" + 
				"		Connection conn = null;\r\n" + 
				"		Statement stat = null;\r\n" + 
				"		ResultSet rs = null;\r\n" + 
				"\r\n" + 
				"		JSONObject object = new JSONObject();\r\n" + 
				"		try {\r\n" + 
				"			conn = getConnection();\r\n" + 
				"			stat = conn.createStatement();\r\n" + 
				"\r\n" + 
				"			String query = \"select * from \" + tableName + \" where "+ table.getPrimaryKey() +" like ?\";\r\n" + 
				"		    PreparedStatement pstmt = conn.prepareStatement(query);\r\n" + 
				"		    pstmt.setString(1, id);\r\n" + 
				"		   \r\n" + 
				"		    rs = pstmt.executeQuery();\r\n" + 
				"		    \r\n" + 
				"		    while (rs.next()) {\r\n" + 
				"				for ("+getClassName()+"Column entityColumn : "+getClassName()+"Column.values()) {\r\n" + 
				"					if (rs.getObject(entityColumn.name()) != null) {\r\n" + 
				"						object.put(entityColumn.name(), getObjectForColumn(rs, entityColumn));\r\n" + 
				"					} else {\r\n" + 
				"						object.put(entityColumn.name(), \"\");\r\n" + 
				"					}\r\n" + 
				"				}\r\n" + 
				"				return object;\r\n" + 
				"			}\r\n" + 
				"		    \r\n" + 
				"	    	object.put(\"message\", \"no recored found\");\r\n" + 
				"	    	return object;\r\n" + 
				"		      \r\n" + 
				"		} catch (SQLException sqle) {\r\n" + 
				"			throw new RuntimeException(sqle.getMessage(), sqle);\r\n" + 
				"		} finally {\r\n" + 
				"			closeConn(conn, stat, rs);\r\n" + 
				"		}\r\n" + 
				"	}");
	}

	@Override
	public void writeVariables() {
		
	}
	
	@Override
	public void writeCode() {
		writepackage();
		writeImport();
		blankLine();
		writeClass();
		blankLine();
		writeVariables();
		blankLine();
		writeMethods();
		blankLine();
		writeEndLine();
		closePrinter();
	}
	
}
