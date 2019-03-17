package com.fspacet.Restassured;

import java.io.IOException;

import org.junit.Test;

public class Batchopen {

	
	
	@Test
	public void batch()
	{
		try {
	        String[] command = {"cmd.exe", "/C", "Start", "C:\\subash\\wiremo.bat"};
	        Process p =  Runtime.getRuntime().exec(command);           
	    } catch (IOException ex) 
		
		{
	    }
	}
}
