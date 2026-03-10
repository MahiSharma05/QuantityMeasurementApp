package com.apps.quantitymeasurement;

public class Volume {

    private double value;
    private VolumeUnit unit;

    public Volume(double value, VolumeUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value.");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public VolumeUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

}