package com.rsr.service;

import static com.rsr.ServiceConstants.CONTROLLER;
import static com.rsr.ServiceConstants.REST;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.rsr.domain.Method;
import com.rsr.domain.Table;


@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RestCodeWriter extends CodeWriter {

	public RestCodeWriter(Table table, String projectName) {
		super(table, projectName);
	}

	@Override
	String getfilePath() {
		return REST + "\\" + getClassName();
	}

	@Override
	String getClassName() {
		return table.getTableClassName() + CONTROLLER;
	}
	
	@Override
	public void writepackage() {
		pr.println("package com.rsr.rest;");
	}

	@Override
	protected void writeImport() {
		super.writeImport();
		pr.println("import com.rsr.entity.DBEntity;\r\n" + 
				"import com.rsr.entity."+ table.getTableClassName() + "Entity;");
	}

	@Override
	public void writeClass() {
		pr.println("@Path(\"/"+ table.getRestPath() +"\")\r\n" + 
				"public class "+ getClassName() +" {");
	}

	@Override
	public void writeVariables() {
		pr.println("private static final String TABLE_NAME = \""+ table.getTablename() +"\"; \r\n" + 
				"	private static final String PRIMARY_KEY = \"" + table.getPrimaryKey() + "\";");
	}

	@Override
	public void writeMethods() {
		
		for (Method req : table.getRequests()) {
			switch (req) {
			case GET:
				getwrite();
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
				break;
			default:
				break;
			}
			
		}
	}

	private void deleteWrite() {
		pr.println("@DELETE\r\n" + 
				"	@Path(\"/{id}\")\r\n" + 
				"	@Produces(MediaType.APPLICATION_JSON)\r\n" + 
				"	public Response deleteEntity(@PathParam(\"id\") String id) throws JsonGenerationException, JsonMappingException, IOException, JSONException {\r\n" + 
				"		\r\n" + 
				"		DBEntity entity = new "+ table.getTableClassName() + "Entity" +"();\r\n" + 
				"		JSONObject jsonEntity = entity.deleteEntity(TABLE_NAME, id);\r\n" + 
				"		return Response.status(200).entity(jsonEntity).build();\r\n" + 
				"	}");
	}

	private void postWrite() {
		pr.println("@POST\r\n" + 
				"	@Path(\"/\")\r\n" + 
				"	@Consumes(MediaType.APPLICATION_JSON)\r\n" + 
				"	@Produces(MediaType.APPLICATION_JSON)\r\n" + 
				"	public Response postEntity(JSONObject entity) throws JSONException {\r\n" + 
				"		\r\n" + 
				"		DBEntity claimHeaderEntity = new "+ table.getTableClassName() + "Entity" + "();\r\n" + 
				"		claimHeaderEntity.validateEntity(entity, PRIMARY_KEY);\r\n" + 
				"		claimHeaderEntity.postEntity(TABLE_NAME, PRIMARY_KEY, entity);\r\n" + 
				"		return Response.status(200).entity(entity).build();\r\n" + 
				"	}");
	}

	private void getByIdWrite() {
		pr.println("@GET\r\n" + 
				"	@Path(\"/{id}\")\r\n" + 
				"	@Produces(MediaType.APPLICATION_JSON)\r\n" + 
				"	public Response getEntity(@PathParam(\"id\") String id) throws JsonGenerationException, JsonMappingException, IOException, JSONException {\r\n" + 
				"		\r\n" + 
				"		DBEntity entity = new "+ table.getTableClassName() + "Entity" +"();\r\n" + 
				"		JSONObject jsonEntity = entity.getEntity(TABLE_NAME, id);\r\n" + 
				"		return Response.status(200).entity(jsonEntity).build();\r\n" + 
				"	}");
	}

	private void getwrite() {
		pr.println("@GET\r\n" + 
				"	@Path(\"/\")\r\n" + 
				"	@Produces(MediaType.APPLICATION_JSON)\r\n" + 
				"	public Response getEntities() throws JsonGenerationException, JsonMappingException, IOException, JSONException {\r\n" + 
				"		\r\n" + 
				"		DBEntity entity = new "+ table.getTableClassName() + "Entity" +"();\r\n" + 
				"		JSONArray jsonEntity = entity.getEntities(TABLE_NAME);\r\n" + 
				"		return Response.status(200).entity(jsonEntity).build();\r\n" + 
				"	}");
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
