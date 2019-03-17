package Stubtest;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.response.Response;

public class Wiremock {
	
	
    public void wiremmock(String url)
    
    {
	
	Response response = given().with().get(url);
	
	String S1 = response.getBody().asString();;
	
	System.out.println("body is " +S1);
	 
    System.out.println("C1 Calling Producer2: ");
    
	
	
}
    

}
