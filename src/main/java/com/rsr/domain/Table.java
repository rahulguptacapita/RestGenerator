
package com.rsr.domain;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rsr.TableDaoImpl;


import static com.rsr.ServiceConstants.GET;
import static com.rsr.ServiceConstants.GETBYID;
import static com.rsr.ServiceConstants.PUT;
import static com.rsr.ServiceConstants.DELETE;
import static com.rsr.ServiceConstants.POST;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "requests", "tablename" })
public class Table {


	@JsonProperty("requests")
	private List<String> requests = null;
	@JsonProperty("tablename")
	private String tablename;

	private String primaryKey;

	private String tableClassName;
	
	private static String tableObjectName = "entity";
	
	private String restPath;

	@JsonProperty("columns")
	private List<Column> columns = null;
	
	
	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(TableDaoImpl tableDaoImpl) throws SQLException {
		this.columns = tableDaoImpl.getTableColumns(tablename);
	}

	public String getRestPath() {
		return restPath;
	}

	public void setRestPath() {
		this.restPath = toLowerCase(tablename);
	}

	private String toLowerCase(String s) {
		String[] parts = s.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString = camelCaseString + part.toLowerCase();
		}
		return camelCaseString;
	}

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getTableClassName() {
		return tableClassName;
	}

	public void setTableClassName() {
		if(tablename != null) {
			this.tableClassName = toCamelCase(tablename);
		}
	}
	
	private String toCamelCase(String s) {
		String[] parts = s.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString = camelCaseString + toProperCase(part);
		}
		return camelCaseString;
	}

	public String getTableObjectName() {
		return tableObjectName;
	}

	public void setTableObjectName(String tableObjectName) {
		this.tableObjectName = tableObjectName;
	}

	private String toProperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(TableDaoImpl tableDaoImpl) throws SQLException {
		this.primaryKey = tableDaoImpl.findPrimaryKey(tablename);
	}

	@JsonProperty("requests")
	public List<String> getRequests() {
		return requests;
	}

	@JsonProperty("requests")
	public void setRequests(List<String> requests) {
		this.requests = requests;
	}

	@JsonProperty("tablename")
	public String getTablename() {
		return tablename;
	}

	@JsonProperty("tablename")
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public void upperCaseTablename() {
		if (this.tablename != null) {
			this.tablename = this.tablename.toUpperCase();
		}
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void validateTable(TableDaoImpl tableDaoImpl) throws SQLException {
		if (!tableDaoImpl.isTableExist(getTablename())) {
			throw new RuntimeException(getTablename() + " does not exist ");
		}
		
		for (String r : getRequests()) {
			if(r.equalsIgnoreCase(GET) || r.equalsIgnoreCase(GETBYID) || r.equalsIgnoreCase(POST) || r.equalsIgnoreCase(PUT) || r.equalsIgnoreCase(DELETE)) {
				continue;
			}
			throw new RuntimeException("Invalid method");
		}

	}

}
