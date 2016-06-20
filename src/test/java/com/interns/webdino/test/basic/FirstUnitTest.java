package com.interns.webdino.test.basic;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
public class FirstUnitTest {

	@Before
	public void setUP()
	{
		System.out.println("Preparing for Unit Test...");
	}
	@Test
	public void FirstUnitTest(){
		System.out.println("This is my first Unit Test");
		
	}
	@Test
	public void SecondUnitTest(){
		System.out.println("This is my second Unit Test");
		
	}
	
	@Test
	public void testMap(){
		Map<String, String> myMap = new HashMap<String, String>();
		assertNotNull(myMap);
		assertTrue(myMap.isEmpty());
		//assertFalse(myMap.isEmpty());
		assertEquals(1, myMap.size());
	}

}
