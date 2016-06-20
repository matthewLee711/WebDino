package com.interns.webdino.perftest;

import static org.junit.Assert.*;


import org.junit.Test;

public class PriceParserTest {
	
	@Test
	public void testPriceParser(){
		//Range range = PriceParser.parse("$100 - $149.99");
		//assertEquals(new Range(100, 149.99), range);
	}
	@Test
	public void testPriceParserNoDecimal(){
		//Range range = PriceParser.parse("$100 - $149");
		//assertEquals(new Range(100, 149), range);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testEmptyString(){
		//Range range = PriceParser.parse("");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testnullPtr(){
		//Range range = PriceParser.parse(null);
	}
	
}
