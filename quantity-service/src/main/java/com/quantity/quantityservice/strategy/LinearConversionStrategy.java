package com.quantity.quantityservice.strategy;

import com.quantity.quantityservice.core.ConversionStrategy;
import com.quantity.quantityservice.core.IMeasurable;
import com.quantity.quantityservice.exception.IncompatibleUnitsException;

/**
 * Handles conversion for all LINEAR unit types: Length, Weight, Volume.
 *
 * Formula:
 *   baseValue   = value × from.conversionFactor
 *   resultValue = baseValue ÷ to.conversionFactor
 *
 * Example  2 FEET → INCH:
 *   base   = 2 × 12  = 24 INCH (base)
 *   result = 24 ÷  1 = 24 INCH   ✓
 *
 * Example  1 KILOGRAM → GRAM:
 *   base   = 1 × 1_000_000 MILLIGRAM (base)
 *   result = 1_000_000 ÷ 1000 = 1000 GRAM  ✓
 */
public class LinearConversionStrategy implements ConversionStrategy {

    /** Singleton — stateless, safe to share */
    public static final LinearConversionStrategy INSTANCE = new LinearConversionStrategy();

    private LinearConversionStrategy() {}

    @Override
    public double convert(double value, IMeasurable from, IMeasurable to) {
        // Guard: both units must belong to the same measurement type
        if (!from.getMeasurementType().equals(to.getMeasurementType())) {
            throw new IncompatibleUnitsException(
                    from.getMeasurementType(), to.getMeasurementType());
        }

        double baseValue = value * from.getConversionFactor();
        return baseValue / to.getConversionFactor();
    }
}
