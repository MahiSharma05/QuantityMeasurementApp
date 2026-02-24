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
  
| Input            | Expected Result   |  
| ---------------- | ----------------- |
| 1.0 ft vs 1.0 ft | Equal (true)      |
| 2.0 ft vs 3.0 ft | Not Equal (false) |


- Focus:

 * Overriding equals()

 * Basic OOP structure

 * Object comparison logic

### UC2 – Inches & Cross Comparison

- Objective:
  Introduce Inches and enable comparison between Feet and Inches.

- Example:
  
| Input             | Expected Result   |
| ----------------- | ----------------- |
| 1 ft vs 12 inches | Equal (true)      |
| 2 ft vs 24 inches | Equal (true)      |
| 1 ft vs 10 inches | Not Equal (false) |


- Focus:

  Unit conversion logic

  Validation handling

  Identifying code duplication issue

### UC3 – Generic Length Class (DRY Applied)

- Objective:
Refactor duplicate classes into a single unified Length class using enum-based unit handling.

- Example:
  
| Input             | Expected Result   |
| ----------------- | ----------------- |
| 1 ft vs 12 inches | Equal (true)      |
| 3 ft vs 36 inches | Equal (true)      |
| 5 ft vs 50 inches | Not Equal (false) |


- Focus:

 DRY Principle

 Enum for unit representation

 Base-unit conversion strategy


### UC4 – Extended Unit Support

- Objective:
  Extend the system to support additional units: Yards and Centimeters.

- Example:
  
| Input                  | Expected Result   |
| ---------------------- | ----------------- |
| 1 yard vs 36 inches    | Equal (true)      |
| 3 feet vs 1 yard       | Equal (true)      |
| 100 cm vs 39.37 inches | Equal (true)      |
| 30 cm vs 1 foot        | Not Equal (false) |


- Focus:

 System extensibility

 Backward compatibility

 Floating-point precision handling

### UC5 – Unit Conversion Feature

- Objective:
  Implement generic conversion functionality between any supported units.

- Example:
  
| Input                | Converted Output |
| -------------------- | ---------------- |
| 2 feet → yards       | 0.67 yards       |
| 36 inches → yards    | 1 yard           |
| 30.48 cm → feet      | 1 foot           |
| 2 feet → centimeters | 60.96 cm         |



- Focus:

  Reusable conversion method

  Rounding logic

  Exception handling


