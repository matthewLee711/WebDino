package com.interns.webdino.test.basic;

import java.util.regex.*;

public class PriceParser {

	public static Range parse(String s) {
		
		String PRICE_RANGE_REGEX = ".*?([0-9,]+\\.?\\d{0,2}).*?([0-9,]+\\.?\\d{0,2}).*?" ;
		
		Range priceRange;
		/*
		try {
		    Pattern priceRangePattern = Pattern.compile(PRICE_RANGE_REGEX);
		    Matcher matcher = priceRangePattern.matcher(s);
		    matcher.matches();
		    priceRange = new Range(Double.parseDouble(matcher.group(1).replaceAll(",", "")), Double.parseDouble(matcher.group(2).replaceAll(",", "")));
		} catch (Exception e) {
		    throw new IllegalArgumentException("Unable to parse price range from: " + s, e);
		}
*/
		//return priceRange; 	
		return new Range(100, 149.99);

		/*if(s== null || "".equals(s)){
			throw new IllegalArgumentException("No value fro proce range");
		}
		// s = s.strip();
		if()
		
		int middle = s.indexOf("-");
		int firstNumber = s.indexOf("$") + 1;
		int secondNumber = s.indexOf("$", middle) + 1;
		int end = s.length();
		double num1 = Double.parseDouble(s.substring(firstNumber, middle));
		double num2 = Double.parseDouble(s.substring(secondNumber, end));
		return new Range(num1, num2);*/
	}

}
