package com.fspacet.Restassured;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.springframework.http.HttpMethod.GET;

import java.rmi.ConnectException;

import static com.jayway.restassured.RestAssured.given;

public class Wiremock {
	
	   private final RestTemplate restTemplate = new RestTemplate(); 
	   
	   
	   
	   @Test
	   public void test2()
	   {
		   
	        
			   given()
			   .when().post("http://localhost:8080/api/mytest");
	          
	           
		   }
	   
	   
	   
	   @Test
	   public void test1()
	   {
		   try {
	        
			   given()
			   .when().get("http://localhost:8080/api/mytest");
	          
	           
	           fail("This should have thrown an exception");

	       } catch(Exception e) {
	           assertTrue(e.getMessage().equals("Connection refused: connect"));
	           
	           
	           
	       }
		    
		  
	   }
	   
	   
		   @Test 
	   
	   public void test()
	   {
		   ResponseEntity<String> exchange = 
				   restTemplate.exchange("http://localhost:8080/api/mytest",
				   GET, null, new ParameterizedTypeReference<String>() {});
		   assertTrue( exchange.getBody().equals("API Test")); 
	     
	   }
	   
	   
} 
