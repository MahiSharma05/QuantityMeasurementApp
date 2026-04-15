package com.quantity.quantityservice.strategy;

import com.quantity.quantityservice.core.ConversionStrategy;
import com.quantity.quantityservice.core.IMeasurable;
import com.quantity.quantityservice.exception.IncompatibleUnitsException;
import com.quantity.quantityservice.measurement.TemperatureUnit;

/**
 * ★ Handles temperature conversion using exact formulas.
 *
 * ── Why linear formula doesn't work for temperature ─────────────────────────
 *   Linear:  result = value × factor_from / factor_to
 *   For 0°C → °F:  0 × anything = 0,  but correct answer is 32°F.
 *   The offset (+32 / -32) is lost in pure multiplication.
 * ────────────────────────────────────────────────────────────────────────────
 *
 * Formulas used:
 *   Celsius    → Fahrenheit : F = (C × 9/5) + 32
 *   Fahrenheit → Celsius    : C = (F − 32) × 5/9
 *   Same unit  → same unit  : return value unchanged
 */
public class TemperatureConversionStrategy implements ConversionStrategy {

    /** Singleton — stateless, safe to share */
    public static final TemperatureConversionStrategy INSTANCE =
            new TemperatureConversionStrategy();

    private TemperatureConversionStrategy() {}

    @Override
    public double convert(double value, IMeasurable from, IMeasurable to) {

        // Both must be temperature units
        if (!from.getMeasurementType().equals("TEMPERATURE") ||
                !to.getMeasurementType().equals("TEMPERATURE")) {
            throw new IncompatibleUnitsException(
                    from.getMeasurementType(), to.getMeasurementType());
        }

        // Safe cast — UnitRegistry guarantees these are TemperatureUnit instances
        TemperatureUnit fromUnit = (TemperatureUnit) from;
        TemperatureUnit toUnit   = (TemperatureUnit) to;

        // Same unit → no conversion needed
        if (fromUnit == toUnit) {
            return value;
        }

        // ★ Celsius → Fahrenheit
        if (!fromUnit.isFahrenheit() && toUnit.isFahrenheit()) {
            return celsiusToFahrenheit(value);
        }

        // ★ Fahrenheit → Celsius
        if (fromUnit.isFahrenheit() && !toUnit.isFahrenheit()) {
            return fahrenheitToCelsius(value);
        }

        // Fallback — should never reach here given only 2 temperature units
        return value;
    }

    // ── Conversion formulas ───────────────────────────────────────────────────

    /**
     * Celsius to Fahrenheit.
     * Formula: F = (C × 9/5) + 32
     *
     * Test cases:
     *   0°C   → 32°F
     *   100°C → 212°F
     *   -40°C → -40°F  (the crossover point)
     */
    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32.0;
    }

    /**
     * Fahrenheit to Celsius.
     * Formula: C = (F − 32) × 5/9
     *
     * Test cases:
     *   32°F  → 0°C
     *   212°F → 100°C
     *   -40°F → -40°C
     */
    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }
}