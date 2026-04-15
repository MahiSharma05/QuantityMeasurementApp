package com.quantity.quantityservice.core;

/**
 * Strategy interface for unit conversion.
 *
 * Two implementations exist:
 *   LinearConversionStrategy    — for Length, Weight, Volume
 *   TemperatureConversionStrategy — for Temperature
 *
 * The service picks the right strategy via UnitRegistry or IMeasurable.isTemperature().
 */
public interface ConversionStrategy {

    /**
     * Converts a value from one unit to another.
     *
     * @param value    the input amount
     * @param from     the source unit
     * @param to       the target unit
     * @return         the converted value
     */
    double convert(double value, IMeasurable from, IMeasurable to);
}