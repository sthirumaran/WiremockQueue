package com.fspacet.Restassured;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.Test;

public class Shutdown {
	
	
	
	
	@Test
	   public void test3()
	   {
		   
	        
		  given().
          when().
          get("http://localhost:8066/api/mytest").
          then().
          assertThat().statusCode(200);   			   
		  
			System.out.println(" Server 8080 working FINE");
	           
		   }
	@Test
	   public void test7() throws InterruptedException
	   {
		   
	        
			   given()
			   .when().post("http://localhost:8066/__admin/shutdown");
	          Thread.sleep(5000);
	           
		   }
	
	@Test
	public void tes() throws InterruptedException
	{
	try {
        String[] command = {"cmd.exe", "/C", "Start", "C:\\subash\\wiremo.bat"};
                Process p =  Runtime.getRuntime().exec(command);    
                Thread.sleep(5000);
        
    } catch (IOException ex) 
	
	{
    }
	
	}
	
	@Test
	   public void test4()
	   {
		   
	        
		  given().
       when().
       get("http://localhost:8066/api/mytest").
       then().
       assertThat().statusCode(200);   			   
		  
			System.out.println(" Server 8080 working FINE");
			
			
	           
		   }
}
