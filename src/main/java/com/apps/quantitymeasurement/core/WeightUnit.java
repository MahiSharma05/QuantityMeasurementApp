package com.apps.quantitymeasurement.core;

public enum WeightUnit implements IMeasurable {
	// Conversion factor to the base unit(grams)
	MILIGRAM(0.001), GRAM(1.0), KILOGRAM(1000.0), POUND(453.592), TONNE(1_000_000.0);

	private final double conversionFactor;

	private WeightUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public double getConversionFactor() {
		return conversionFactor;
	}
	
	@Override
	public String getUnitName() {
		return this.toString();
	}
	
	/**
	 * Convert the length value to base unit value
	 */
	public double convertToBaseUnit(double value) {
	    return value * this.conversionFactor;
	}
	
	/**
	  * Convert base unit value to specified unit
	  */
	public double convertFromBaseUnit(double baseValue) {
		return Math.round((baseValue / this.conversionFactor) * 100.0) / 100.0;
	}
	@Override
	public String getMeasurementType() {
	    return this.getClass().getSimpleName();
	}

	@Override
	public IMeasurable getUnitInstance(String unitName) {

	    for (WeightUnit unit : WeightUnit.values()) {

	        if (unit.getUnitName().equalsIgnoreCase(unitName)) {
	            return unit;
	        }
	    }

	    throw new IllegalArgumentException("Invalid weight unit: " + unitName);
	}
	// Main method to test WeightUnit enum
	public static void main(String[] args) {
		double kilograms = 5.0;
		double grams = WeightUnit.KILOGRAM.convertToBaseUnit(kilograms);
		System.out.println(kilograms + " kilograms are equal to " + grams + " grams");
		
		double miligrams = WeightUnit.MILIGRAM.convertFromBaseUnit(grams);
		System.out.println(miligrams);
		
	}
}