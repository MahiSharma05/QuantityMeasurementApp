package com.app.quantitymeasurement.dto;

public class QuantityDTO {

    private double value;
    private String unit;
    private String measurementType;

    //  REQUIRED
    public QuantityDTO() {}

    //  OPTIONAL
    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    //  GETTERS & SETTERS (VERY IMPORTANT)
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }
}