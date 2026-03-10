package com.apps.quantitymeasurement;

public interface IMeasurable {

	public double getConversionFactor();
	
	public double convertToBaseUnit (double value);

	public double convertFromBaseUnit (double baseValue);
	
	// main method to test IMeasurable interface	
	public static void main(String[] args) {
		System.out.println("IMeasurable Interface");
	}
}