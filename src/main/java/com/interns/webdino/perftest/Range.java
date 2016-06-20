package com.interns.webdino.perftest;

public class Range {
	private int low;
    private int high;

    public Range(double num1, double num2){
        this.low = (int) num1;
        this.high = (int) num2;
    }

    public boolean contains(int number){
        return (number >= low && number <= high);
    }
}
