package com.fspacet.Restassured;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

import com.jayway.restassured.response.Response;


public class Responsetest {

	@Test	
	public void test1()
	{
	
	String URL = "http://localhost:8080/api/mytest1";
	
	Response response = given().with().get(URL);
	
	String S1 = response.getBody().asString();;
	
	System.out.println("body is " +S1);
	
	
}
}
