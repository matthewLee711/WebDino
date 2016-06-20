package com.interns.webdino.test.basic;
import static org.junit.Assert.*;

import org.junit.Test;


public class PriceParserTest {
	
	@Test
	public void testPriceParser(){
		Range range = PriceParser.parse("$100 - $149.99");
		assertEquals(new Range(100, 149.99), range);
	}
	
	@Test
	public void testSpacesBetweenDashes(){
		Range range = PriceParser.parse("$100   -   $149.99");
		assertEquals(new Range(100, 149.99), range);
	}
	
	@Test
	public void testPriceDecimal(){
		Range range = PriceParser.parse("$100 - $149");
		assertEquals(new Range(100, 149), range);
	}
	
	@Test
	public void testPriceCommas(){
		Range range = PriceParser.parse("$1,000 - $1049.99");
		assertEquals(new Range(1000, 1049.99), range);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testEmptyString(){
		Range range = PriceParser.parse("");
		assertEquals(new Range(100, 149.99), range);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNullArgument(){
		Range range = PriceParser.parse(null);
		assertEquals(new Range(100, 149.99), range);
	}
	
	
}
