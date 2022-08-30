package org.jira;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class Jira {
	static String jira_id ;
	static String issue ;
	static String body;
     SessionFilter session = new SessionFilter();
	
	
	@Test(priority=1)
	public void sessioncreation () {
		RestAssured.baseURI="http://localhost:8080";
		
		String sessionresponse = given().log().all().header("Content-Type","application/json").body("{ \"username\": \"bharathikannan\", \"password\": \"bharathi@005\" }")
        .filter(session).when().post("/rest/auth/1/session").then().log().all().assertThat().statusCode(200).extract().response()
        .asString();
		
	
	}
	@Test(priority=2)
	public void createIssue() {
		String createresponse = given().log().all().header("Content-Type","application/json").filter(session).body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"RES\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"login not working.\",\r\n"
				+ "       \"description\": \"Creating of an issue using ids for projects and issue types using the REST API\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}").when().post("/rest/api/2/issue").then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath j = new JsonPath(createresponse);
		issue = j.getString("issuetype.name");
	     jira_id = j.get("id");
		
	}
	@Test(priority=3)
	public void getIssue() {
		given().log().all().pathParam("jira_id",issue)
		.header("Content-Type","application/json").filter(session).
		when().get("/rest/api/2/issue/{jira_id}").then().log().all().assertThat().statusCode(200);
	}
	
	@Test(priority=4)
	public void addComment() {
		String commentresponse = given().log().all().pathParam("jira_id",jira_id).header("Content-Type","application/json").filter(session).body("{\r\n"
				+ "    \"body\": \"this is my first work in eclipse.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").
		when().post("/rest/api/2/issue/{jira_id}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath j = new JsonPath(commentresponse);
	     body = j.get("id");
	}
	
	@Test(priority=5)
	public void getComment() {
		given().log().all().pathParam("jira_id",body).header("Content-Type","application/json").filter(session).
		when().get("/rest/api/2/issue/{jira_id}/comment")
		.then().log().all().assertThat().statusCode(200);
	}
}

