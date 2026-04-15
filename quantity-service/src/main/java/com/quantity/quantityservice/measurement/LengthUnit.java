package com.quantity.quantityservice.measurement;

import com.quantity.quantityservice.core.IMeasurable;

/** Base unit: INCH */
public enum LengthUnit implements IMeasurable {

    INCH(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701),
    METER(39.3701),
    KILOMETER(39370.1),
    MILE(63360.0);

    private final double factor;
    LengthUnit(double factor) { this.factor = factor; }

    @Override public String getUnitName()         { return this.name(); }
    @Override public String getMeasurementType()  { return "LENGTH"; }
    @Override public double getConversionFactor() { return factor; }
}