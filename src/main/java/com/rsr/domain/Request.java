
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "projectname",
    "tables"
})
public class Request {

    @JsonProperty("projectname")
    private String projectname;
    @JsonProperty("tables")
    private List<Table> tables = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("projectname")
    public String getProjectname() {
        return projectname;
    }

    @JsonProperty("projectname")
    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @JsonProperty("tables")
    public List<Table> getTables() {
        return tables;
    }

    @JsonProperty("tables")
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    
    }
    
    
    public void validateRequest(TableDaoImpl tableDaoImpl) throws SQLException {

    	if(getProjectname() == null || getProjectname().isEmpty() ) {
    		throw new RuntimeException("Project name is empty");
    	}
    	
		if (getTables() == null) {
			throw new RuntimeException("Tables object required");
		}

		if (getTables().size() == 0) {
			throw new RuntimeException("no tables found");
		}

		
		List<Table> tables = getTables();
		for (Table table : tables) {
			table.validateTable(tableDaoImpl);
		}

	}

	public void enrichRequest(TableDaoImpl tableDaoImpl) throws SQLException {
		for (Table table : getTables()) {
			table.setPrimaryKey(tableDaoImpl);
			table.upperCaseTablename();
			table.setTableClassName();
			table.setRestPath();
			table.setColumns(tableDaoImpl);
		}
	}
}
