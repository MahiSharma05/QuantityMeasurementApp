# QuantityMeasurementApp


The Quantity Measurement App is a Java-based project designed to demonstrate comparison and conversion of different measurement units in a structured and scalable way.

This project is being developed incrementally through multiple Use Cases (UCs).
Each use case is implemented in a separate feature branch to simulate real-world development workflows and maintain clean version control history.

The application evolves step by step, improving design, structure, scalability, and maintainability with every new use case.


## Concepts Practiced

- Object-Oriented Programming

- Encapsulation

- Method Overriding

- Enum Usage

- DRY Principle


## Branch Structure

- feature/UC1-FeetEquality

- feature/UC2-InchEquality

- feature/UC3-GenericLength

- feature/UC4-YardEquality

- feature/UC5-UnitConversion


## Use Case Breakdown
### UC1 – Feet Equality

- Objective:
  Implement basic equality comparison between two length objects in Feet.

- Example:
  1.0 ft == 1.0 ft → true

- Focus:

 * Overriding equals()

 * Basic OOP structure

 * Object comparison logic

### UC2 – Inches & Cross Comparison

- Objective:
  Introduce Inches and enable comparison between Feet and Inches.

- Example:
  1 ft == 12 inches → true

- Focus:

  Unit conversion logic

  Validation handling

  Identifying code duplication issue

### UC3 – Generic Length Class (DRY Applied)

- Objective:
Refactor duplicate classes into a single unified Length class using enum-based unit handling.

- Example:
  1 ft == 12 inches → true

- Focus:

 DRY Principle

 Enum for unit representation

 Base-unit conversion strategy


### UC4 – Extended Unit Support

- Objective:
  Extend the system to support additional units: Yards and Centimeters.

- Example:
  1 yard == 36 inches → true
  3 feet == 1 yard → true
  100 cm ≈ 39.37 inches → true

- Focus:

 System extensibility

 Backward compatibility

 Floating-point precision handling

### UC5 – Unit Conversion Feature

- Objective:
  Implement generic conversion functionality between any supported units.

- Example:
  2 feet → 0.67 yards
  36 inches → 1 yard
  30.48 cm → 1 foot

- Focus:

  Reusable conversion method

  Rounding logic

  Exception handling


