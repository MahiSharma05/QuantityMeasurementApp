package com.quantity.quantityservice.measurement;

import com.quantity.quantityservice.core.IMeasurable;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(false),
    FAHRENHEIT(true);

    private final boolean isFahrenheit;

    TemperatureUnit(boolean isFahrenheit) {
        this.isFahrenheit = isFahrenheit;
    }

    public boolean isFahrenheit() {
        return isFahrenheit;
    }

    // ── IMeasurable contract ──────────────────────────────────────────────────

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return "TEMPERATURE";
    }

    /**
     * Not used for temperature — conversion is handled by TemperatureConversionStrategy.
     * Returning 1.0 to satisfy the interface contract safely.
     */
    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    /**
     * ★ Key override — tells the service to use TemperatureConversionStrategy
     *   instead of LinearConversionStrategy.
     */
    @Override
    public boolean isTemperature() {
        return true;
    }
}