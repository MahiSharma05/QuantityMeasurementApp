package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    private static final double FEET_TO_INCH = 12.0;

    // FEET CLASS 
    public static class Feet {
        private final double value;

        public Feet(double value) {
            validate(value);
            this.value = value;
        }

        private void validate(double value) {
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value for Feet");
            }
        }

        public double toInches() {
            return value * FEET_TO_INCH;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (getClass() != obj.getClass())
                return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    // INCHES CLASS
    public static class Inches {
        private final double value;

        public Inches(double value) {
            validate(value);
            this.value = value;
        }

        private void validate(double value) {
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value for Inches");
            }
        }

        public double toFeet() {
            return value / FEET_TO_INCH;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (getClass() != obj.getClass())
                return false;

            Inches other = (Inches) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    // STATIC METHODS

    // Demonstrate Feet equality 
    public static void demonstrateFeetEquality() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        boolean result = f1.equals(f2);
        System.out.println("1.0 ft and 1.0 ft Equal? " + result);
    }

    // Demonstrate Inches equality
    public static void demonstrateInchesEquality() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(1.0);

        boolean result = i1.equals(i2);
        System.out.println("1.0 inch and 1.0 inch Equal? " + result);
    }

    //  Feet-Inches comparison 
    public static void demonstrateFeetAndInchesEquality() {
        Feet feet = new Feet(1.0);
        Inches inches = new Inches(12.0);

        boolean result = Double.compare(feet.toInches(), 12.0) == 0;
        System.out.println("1.0 ft and 12.0 inch Equal? " + result);
    }

    public static void main(String[] args) {

        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetAndInchesEquality();
    }
}
