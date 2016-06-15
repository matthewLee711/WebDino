package com.interns.webdino.perftest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class UnitTest {
	private CalculatorTest calculator;
	
	@Before
	public void setup(){
		System.out.println("Prep for test");
	}
	
	@Test
	public void firstTest(){
		System.out.println("TEst yo");
		calculator = new CalculatorTest();
		assertEquals(11, calculator.add());
		
		
	}
	
	@Test
	public void boomTest(){
		Map<String, String> stuff = new HashMap<String, String>();
		//
		//assert(stuff.containsKey("hi"));
		assertNotNull(stuff);
		//stuff.put("hi", "AYYYYYYY");
		System.out.println(stuff.get("hi"));
	}
}
