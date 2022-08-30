package org.oauth;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
public class Courses {
	static String[] s1;
	static String token;
	
@Test(priority=1)
public void codeurl() {
String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qgY_pkF-5s0dqP7ZqGQA7Xzc0_uDQqhfAEUgI8Rc9-O_pF6LjE7hK6QkI4aT2gVFQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
String[] s = url.split("code=");
s1= s[1].split("&scope=");
}
@Test(priority=2)
public void accesstoken() {
	String tokenresponse = given().log().all().queryParam("code",s1[0])
	.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
	.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
	.queryParam("grant_type", "authorization_code").urlEncodingEnabled(false)
	.when().post("https://www.googleapis.com/oauth2/v4/token")
	.then().assertThat().statusCode(200).extract().response().asString();
	JsonPath j = new JsonPath(tokenresponse);
    token = j.get("access_token");
	
}
@Test(priority=3)
public void getresponse() {
	String responces = given().log().all().queryParam("access_token", token).
			header("Content_Type","application/json").when().get("https://rahulshettyacademy.com/getCourse.php").then().assertThat()
			.statusCode(200).extract().response().asString();
	System.out.println(responces);
	JsonPath j = new JsonPath(responces);
	int web = j.get("courses.webautomation.size()");
	for (int i = 0; i < web; i++) {
		System.out.println(j.get("courses.webautomation["+i+"]).0"));
		System.out.println(j.get("courses.webautomation["+i+"].1"));
		System.out.println(j.get("courses.webautomation["+i+"].2"));
		System.out.println(0);
		System.out.println(1);
		System.out.println(2);
		System.out.println(web);
		
	}
	int web1 = j.get("courses.api.size()");
	for (int k = 0; k < web; k++) {
		System.out.println(j.get("courses.api["+k+"]).0"));
		System.out.println(j.get("courses.api["+k+"].1"));
		System.out.println(j.get("courses.api["+k+"].2"));
		System.out.println(web1);
		
	}
	int web2 = j.get("courses.webautomation.size()");
	for (int m = 0; m < web; m++) {
		System.out.println(j.get("courses.mobile["+m+"]).0"));
		System.out.println(j.get("courses.mobile["+m+"].1"));
		System.out.println(j.get("courses.mobile["+m+"].2"));
		System.out.println(web2);
		
	}
}
}