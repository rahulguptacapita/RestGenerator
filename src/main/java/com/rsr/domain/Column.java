package com.rsr.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "columnname", "datatype", "jsonbodyname" })
public class Column {

	@JsonProperty("columnname")
	private String columnname;

	@JsonProperty("datatype")
	private String datatype;
	
	@JsonProperty("jsonbodyname")
	private String jsonbodyname;
	
	private String typename;
	
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("columnname")
	public String getColumnname() {
		return columnname;
	}

	@JsonProperty("columnname")
	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	@JsonProperty("datatype")
	public String getDatatype() {
		return datatype;
	}

	@JsonProperty("datatype")
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	@JsonProperty("jsonbodyname")
	public String getJsonbodyname() {
		return jsonbodyname;
	}

	@JsonProperty("jsonbodyname")
	public void setJsonbodyname(String columnName) {
		jsonbodyname = "";
		String[] split = columnName.split("_");
		for (String s : split) {
			jsonbodyname = jsonbodyname + s.toLowerCase();
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

}