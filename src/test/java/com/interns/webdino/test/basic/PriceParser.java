package com.interns.webdino.test.basic;

public class PriceParser {

	public static Range parse(String s) {
		

		if(s== null || "".equals(s)){
			throw new IllegalArgumentException("No value fro proce range");
		}
		// s = s.strip();
		int middle = s.indexOf("-");
		int firstNumber = s.indexOf("$") + 1;
		int secondNumber = s.indexOf("$", middle) + 1;
		int end = s.length();
		double num1 = Double.parseDouble(s.substring(firstNumber, middle));
		double num2 = Double.parseDouble(s.substring(secondNumber, end));
		return new Range(num1, num2);
	}

}
