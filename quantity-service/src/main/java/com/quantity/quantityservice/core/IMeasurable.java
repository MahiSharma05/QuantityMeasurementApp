package com.quantity.quantityservice.core;

/**
 * Base interface for all unit enums.
 * By flagging temperature units here, the service can route to the correct
 * conversion strategy without instanceof checks on concrete enum types.
 */
public interface IMeasurable {

    /** Display name of the unit, e.g. "CELSIUS", "FEET" */
    String getUnitName();

    /** Measurement category: "LENGTH", "WEIGHT", "VOLUME", "TEMPERATURE" */
    String getMeasurementType();

    /**
     * Conversion factor to base unit (used for LINEAR units only).
     * Temperature units return 1.0 — this value is intentionally ignored
     * by TemperatureConversionStrategy.
     */
    double getConversionFactor();

    /**
     * Returns true only for TemperatureUnit values.
     * Default is false so existing enums need no change.
     */
    default boolean isTemperature() {
        return false;
    }
}
