package com.rsr;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rsr.domain.Column;

@Repository
public class TableDaoImpl {

	@Autowired
	DataSource ds;

	public boolean isTableExist(String tableName) throws SQLException {

		Connection connection = ds.getConnection();
		ResultSet tables = connection.getMetaData().getTables(null, null, tableName, null);
		while (tables.next()) {
			String currentTableName = tables.getString("TABLE_NAME");
			if (currentTableName.equalsIgnoreCase(tableName)) {
				return true;
			}
		}
		tables.close();
		connection.close();
		return false;
	}

	public String findPrimaryKey(String tableName) throws SQLException {

		Connection connection = ds.getConnection();
		ResultSet rs = null;
		DatabaseMetaData meta = connection.getMetaData();
		rs = meta.getPrimaryKeys(null, null, tableName);
		while (rs.next()) {
			String columnName = rs.getString("COLUMN_NAME");
			System.out.println("getPrimaryKeys(): columnName=" + columnName);
			return columnName;
		}
		rs.close();
		connection.close();
		throw new RuntimeException("No primary key found for " + tableName);
	}

	public List<Column> getTableColumns(String tableName) throws SQLException {

		Connection connection = ds.getConnection();
		DatabaseMetaData metadata = connection.getMetaData();
		ResultSet resultSet = metadata.getColumns(null, null, tableName, null);
		ArrayList<Column> columns = new ArrayList<Column>();
		
		while (resultSet.next()) {
			String name = resultSet.getString("COLUMN_NAME");
			String type = resultSet.getString("TYPE_NAME");
			String dType = resultSet.getString("DATA_TYPE");
			setColumn(columns, name, type, dType);
			System.out.println("Column name: [" + name + "]; type: [" + type + "];");
		}
		resultSet.close();
		connection.close();
		return columns;
	}

	private void setColumn(ArrayList<Column> columns, String name, String type, String dType) {
		Column column = new Column();
		column.setColumnname(name);
		column.setTypename(type);
		column.setDatatype(dType);
		column.setJsonbodyname(name);
		columns.add(column);
	}

	public List<String> getAllTableName(String prefix) throws SQLException {
		
		Connection connection = ds.getConnection();
		DatabaseMetaData md = connection.getMetaData();
		ResultSet rs = md.getTables(null, null, prefix, null);
		List<String> jArray = new ArrayList<String>();
		while (rs.next()) {
			jArray.add(rs.getString(3));
		}
		rs.close();
		connection.close();
		return jArray;
	}
}
