package com.apps.quantitymeasurement;

public enum VolumeUnit implements IMeasurable {
    // Conversion factor to the base unit
    LITRE(1.0), MILLILITRE(0.001),GALLON(3.78541);

    private final double conversionFactor;

    private VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert the value to base Unit 
    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }
    
    // Convert the value from Base unit
    @Override
    public double convertFromBaseUnit(double baseValue) {
        return Math.round((baseValue / conversionFactor) * 100000.0) / 100000.0;
    }
    
    public String getUnitName() {
        return this.name();
    }
    
    // Main method to test VolumeUnit enum
    public static void main(String[] args) {
        double litre = 1.0;
        double ml = VolumeUnit.MILLILITRE.convertFromBaseUnit(litre);
        System.out.println("1 litre = " + ml + " millilitres");
        double gallon = VolumeUnit.GALLON.convertFromBaseUnit(litre);
        System.out.println("1 litre = " + gallon + " gallons");
        double base = VolumeUnit.GALLON.convertToBaseUnit(1.0);
        System.out.println("1 gallon = " + base + " litres");
    }
}