package com.interns.webdino.test.basic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class calcTest {

	private Calculator calculator;
	
	@Test
	public void addTest(){
		calculator = new Calculator();
		assertEquals(15,calculator.add(5,10));
		assertEquals(12,calculator.add(5,7));
	}
}
