package com.quantity.quantityservice.measurement;

import com.quantity.quantityservice.core.IMeasurable;

/** Base unit: LITRE */
public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;
    VolumeUnit(double factor) { this.factor = factor; }

    @Override public String getUnitName()         { return this.name(); }
    @Override public String getMeasurementType()  { return "VOLUME"; }
    @Override public double getConversionFactor() { return factor; }
}