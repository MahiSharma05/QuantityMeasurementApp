package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.001;

    // IMeasurable Interface Tests
    @Test
    public void testIMeasurable_LengthUnitImplementation() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        double base = q.getUnit().convertToBaseUnit(q.getValue());
        assertEquals(12.0, base, EPSILON);
    }

    @Test
    public void testIMeasurable_WeightUnitImplementation() {
        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        double base = q.getUnit().convertToBaseUnit(q.getValue());
        assertEquals(1000.0, base, EPSILON);
    }

    @Test
    public void testIMeasurable_VolumeUnitImplementation() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        double base = q.getUnit().convertToBaseUnit(q.getValue());
        assertEquals(1.0, base, EPSILON);
    }

    // Equality Tests
    @Test
    public void testEquality_LitreToLitre_SameValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_LitreToLitre_DifferentValue() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testEquality_MillilitreToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_LitreToGallon_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(0.264172, VolumeUnit.GALLON)));
    }

    @Test
    public void testEquality_GallonToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.GALLON)
                .equals(new Quantity<>(3.78541, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_VolumeVsLength_Incompatible() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        assertFalse(volume.equals(length));
    }

    @Test
    public void testEquality_VolumeVsWeight_Incompatible() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(volume.equals(weight));
    }

    @Test
    public void testEquality_NullComparison() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(v.equals(v));
    }

    @Test
    public void testEquality_ZeroValue() {
        assertTrue(new Quantity<>(0.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(0.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testEquality_NegativeVolume() {
        assertTrue(new Quantity<>(-1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testEquality_LargeVolumeValue() {
        assertTrue(new Quantity<>(1000000.0, VolumeUnit.MILLILITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_SmallVolumeValue() {
        assertTrue(new Quantity<>(0.001, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.MILLILITRE)));
    }

    // Conversion Tests
    @Test
    public void testConversion_LitreToMillilitre() {
        double ml = new Quantity<>(1.0, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, ml, EPSILON);
    }

    @Test
    public void testConversion_MillilitreToLitre() {
        double litre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .convertTo(VolumeUnit.LITRE);
        assertEquals(1.0, litre, EPSILON);
    }

    @Test
    public void testConversion_GallonToLitre() {
        double litre = new Quantity<>(1.0, VolumeUnit.GALLON)
                .convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541, litre, EPSILON);
    }

    @Test
    public void testConversion_LitreToGallon() {
        double gallon = new Quantity<>(3.78541, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.GALLON);
        assertEquals(1.0, gallon, EPSILON);
    }

    @Test
    public void testConversion_MillilitreToGallon() {
        double gallon = new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .convertTo(VolumeUnit.GALLON);
        assertEquals(0.264172, gallon, EPSILON);
    }

    @Test
    public void testConversion_SameUnit() {
        double litre = new Quantity<>(5.0, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.LITRE);
        assertEquals(5.0, litre, EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        double ml = new Quantity<>(0.0, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.MILLILITRE);
        assertEquals(0.0, ml, EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        double ml = new Quantity<>(-1.0, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.MILLILITRE);
        assertEquals(-1000.0, ml, EPSILON);
    }

    @Test
    public void testConversion_RoundTrip() {
        Quantity<VolumeUnit> v = new Quantity<>(1.5, VolumeUnit.LITRE);
        double ml = v.convertTo(VolumeUnit.MILLILITRE);
        double litre = new Quantity<>(ml, VolumeUnit.MILLILITRE)
                .convertTo(VolumeUnit.LITRE);
        assertEquals(1.5, litre, EPSILON);
    }

    // Addition Tests
    @Test
    public void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(2.0, VolumeUnit.LITRE));
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_SameUnit_MillilitrePlusMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(500.0, VolumeUnit.MILLILITRE));
        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_MillilitrePlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(1.0, VolumeUnit.LITRE));
        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_GallonPlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .add(new Quantity<>(3.78541, VolumeUnit.LITRE));
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Litre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.LITRE);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.MILLILITRE);
        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    // VolumeUnit Enum Tests

    @Test
    public void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
    }

    @Test
    public void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
    }

    @Test
    public void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
    }

    // Constructor Validation

    @Test
    public void testConstructor_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    // Generic + Scalability Tests

    @Test
    public void testGenericQuantity_VolumeOperations_Consistency() {
        Quantity<VolumeUnit> v =
                new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(1000.0,
                v.convertTo(VolumeUnit.MILLILITRE), EPSILON);
    }

    @Test
    public void testScalability_VolumeIntegration() {
        Quantity<VolumeUnit> v =
                new Quantity<>(2.0, VolumeUnit.LITRE);
        assertEquals(2000.0,
                v.convertTo(VolumeUnit.MILLILITRE), EPSILON);
    }
}