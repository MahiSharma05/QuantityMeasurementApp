package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> boolean demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {

        return q1.equals(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {

        double convertedValue = quantity.convertTo(targetUnit);

        return new Quantity<>(convertedValue, targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2) {

        return q1.add(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        return q1.add(q2, targetUnit);
    }

    public static void main(String[] args) {

        // ================= LENGTH =================

        Quantity<LengthUnit> length1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> length2 = new Quantity<>(2.0, LengthUnit.FEET);

        System.out.println("Length equality: "
                + demonstrateEquality(length1, length2));

        System.out.println("Length conversion: "
                + demonstrateConversion(length1, LengthUnit.FEET));

        System.out.println("Length addition: "
                + demonstrateAddition(length1, length2));

        // Weight

        Quantity<WeightUnit> weight1 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        System.out.println("Weight equality: "
                + demonstrateEquality(weight1, weight2));

        System.out.println("Weight conversion: "
                + demonstrateConversion(weight1, WeightUnit.KILOGRAM));

        System.out.println("Weight addition: "
                + demonstrateAddition(weight1, weight2));

        // Volume 

        Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Volume equality (L ↔ mL): "
                + demonstrateEquality(volume1, volume2));

        System.out.println("Volume conversion (L → mL): "
                + demonstrateConversion(volume1, VolumeUnit.MILLILITRE));

        System.out.println("Volume conversion (Gallon → Litre): "
                + demonstrateConversion(volume3, VolumeUnit.LITRE));

        System.out.println("Volume addition (L + mL): "
                + demonstrateAddition(volume1, volume2));

        System.out.println("Volume addition (L + Gallon → mL): "
                + demonstrateAddition(volume1, volume3, VolumeUnit.MILLILITRE));
    }
}