package com.quantity.quantityservice.measurement;

import com.quantity.quantityservice.core.IMeasurable;
import com.quantity.quantityservice.exception.UnsupportedUnitException;

/**
 * Resolves a unit name String → IMeasurable enum at runtime.
 *
 * ★ TEMPERATURE added: new try/catch block at the bottom.
 *   All existing blocks are untouched.
 */
public class UnitRegistry {

    public static IMeasurable resolve(String unitName) {
        if (unitName == null || unitName.isBlank()) {
            throw new UnsupportedUnitException("Unit name cannot be null or empty");
        }

        String u = unitName.toUpperCase().trim();

        // ── Existing types (unchanged) ────────────────────────────────────────
        try { return LengthUnit.valueOf(u);      } catch (IllegalArgumentException ignored) {}
        try { return WeightUnit.valueOf(u);      } catch (IllegalArgumentException ignored) {}
        try { return VolumeUnit.valueOf(u);      } catch (IllegalArgumentException ignored) {}

        // ── ★ NEW: Temperature ────────────────────────────────────────────────
        try { return TemperatureUnit.valueOf(u); } catch (IllegalArgumentException ignored) {}

        throw new UnsupportedUnitException(
                "Unknown unit: '" + unitName + "'.\n" +
                        "LENGTH   : INCH, FEET, YARDS, CENTIMETERS, METER, KILOMETER, MILE\n" +
                        "WEIGHT   : MILLIGRAM, GRAM, KILOGRAM, POUND, TONNE\n" +
                        "VOLUME   : LITRE, MILLILITRE, GALLON\n" +
                        "TEMPERATURE: CELSIUS, FAHRENHEIT"   // ★ added to error message
        );
    }
}