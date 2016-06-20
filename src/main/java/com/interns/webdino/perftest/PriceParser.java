package com.interns.webdino.perftest;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mockito.internal.stubbing.answers.ThrowsException;


public class PriceParser {
	
	private static final String PRICE_RANGE_REGEX = "\\s*\\$(\\d*,?\\d*\\.??\\d{0,2})\\s*-\\s*\\$(\\d*,?\\d*\\.??\\d{0,2})\\s*";
	
	/*public static Range parse(String s) {
		Range priceRange;
		try {
		    Pattern priceRangePattern = Pattern.compile(PRICE_RANGE_REGEX);
		    Matcher matcher = priceRangePattern.matcher(s);
		    matcher.matches();
		    priceRange = new Range(Double.parseDouble(matcher.group(1).replaceAll(",", "")), Double.parseDouble(matcher.group(2).replaceAll(",", "")));
		} catch (Exception e) {
		    throw new IllegalArgumentException("Unable to parse price range from: " + s, e);
		}

		return priceRange; 
	}*/
	
}
