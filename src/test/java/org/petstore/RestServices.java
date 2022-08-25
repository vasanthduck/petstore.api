package org.petstore;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;

import java.util.Iterator;

import org.petdata.PetDetails;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class RestServices {
static int petid;
static String dogstatus;
@DataProvider(name="pet")
public Object[][]getpet(){
	return new Object[][] {{124,"singam","avalible"}};
}
@Test(priority=1,dataProvider="pet")
public void addpet(int id,String name,String names) {
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	//post
	   String postResponse = given().log().all().header("Content-Type","application/json").body(PetDetails.petdata(id,name,names))
	.when().post("/pet").then().assertThat().statusCode(200).extract().response().asString();
	System.out.println("Response: "+postResponse);
	JsonPath j = new JsonPath(postResponse);
	 petid = j.getInt("id");
	System.out.println("petId: "+id);
	String dog = j.getString("name");
	Object status = j.get("status");
	System.out.println(dog);
	System.out.println(status);
	int catId = j.getInt("category.id");
	System.out.println(catId);
	int size = j.getInt("tags.size()");
	for (int i = 0; i < size; i++) {
		System.out.println(j.get("tags["+i+"].id"));
		System.out.println(j.get("tags["+i+"].name"));
		
	}
	
}
@Test(priority=2)
public void retrievepet() {
	   //get
	given().log().all().header("Content-Type","application/json").pathParam("id",petid)
	.when().get("/pet/{id}")
	.then().log().all().assertThat().statusCode(200);
}
@DataProvider(name="petstatus")
public Object[][]updatepet(){
	return new Object[][] {{124,"singam","sold"}};
}
	//put
@Test(priority=3,dataProvider="petstatus")
public void status(int id, String name,String names) {
	String update = given().log().all().header("Content-Type","application/json").body(PetDetails.petdata(id,name,names))
	.when().put("/pet").then().assertThat().statusCode(200).extract().response().asString();
    System.out.println("responsepet: "+update);
    JsonPath p = new JsonPath(update);
    dogstatus = p.getString("name");
    System.out.println("status: "+names);

}
@Test(priority=4)
public void findbystatus() {
	//get findbystatus
	given().log().all().header("Content-Type","application/json").queryParam("status", dogstatus)
	.when().get("/pet/findByStatus")
	.then().log().all().assertThat().statusCode(200);
}

}
