package com.quantity.quantityservice.measurement;

import com.quantity.quantityservice.core.IMeasurable;

/** Base unit: MILLIGRAM */
public enum WeightUnit implements IMeasurable {

    MILLIGRAM(1.0),
    GRAM(1000.0),
    KILOGRAM(1_000_000.0),
    POUND(453_592.0),
    TONNE(1_000_000_000.0);

    private final double factor;
    WeightUnit(double factor) { this.factor = factor; }

    @Override public String getUnitName()         { return this.name(); }
    @Override public String getMeasurementType()  { return "WEIGHT"; }
    @Override public double getConversionFactor() { return factor; }
}