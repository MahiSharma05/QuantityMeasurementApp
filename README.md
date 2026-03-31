# QuantityMeasurementApp


The Quantity Measurement App is a Java-based project designed to demonstrate comparison and conversion of different measurement units in a structured and scalable way.

This project is being developed incrementally through multiple Use Cases (UCs).
Each use case is implemented in a separate feature branch to simulate real-world development workflows and maintain clean version control history.

The application evolves step by step, improving design, structure, scalability, and maintainability with every new use case.



## Use Case Breakdown

### UC1 – Basic Equality (Feet)


- Created Feet class
- Implemented equals()
- Ensured null safety and type safety
- Learned equality contract

  
### UC2 – Added Inches


- Added separate Inches class
- Identified duplication
- Recognized DRY violation

  
### UC3 – Generic Quantity Class (DRY Principle)

Refactored into:

- Single QuantityLength class
- LengthUnit enum
- Achievements
- Eliminated duplication
- Enabled cross-unit equality
- Improved scalability
- Clean architecture

  
### UC4 – Extended Unit Support


 Added:

- YARD
- CENTIMETER
- Without modifying QuantityLength.

Demonstrates:

- Enum extensibility
- Backward compatibility
- Multi-unit comparison
- Transitive equality

  
### UC5 – Explicit Unit Conversion API


Introduced:

- Static convert() method
- Instance convertTo() method
- Method overloading
- Input validation
- Zero, negative, and large value support


### UC6 – Addition of Two Length Units


- Implemented addition of two Length objects
- Used base unit (inches) for internal calculation
- Converted both operands to base unit before addition
- Converted result back to first operand’s unit
- Maintained immutability (returned new object)

  
### UC7 – Addition with Explicit Target Unit


- Added support for specifying target unit in addition
- Implemented add(length1, length2, targetUnit)
- Converted result into desired unit
- Improved flexibility for arithmetic operations

  
### UC8 – Refactoring Unit Enum


- Extracted LengthUnit into standalone enum
- Moved conversion logic from class to enum
- Applied Single Responsibility Principle
- Improved separation of concerns

  
### UC9 – Weight Measurement Support


- Added new category: Weight
- Introduced WeightUnit enum (kg, g, lb)
- Implemented equality, conversion, and addition
- Ensured no comparison between different categories

  
### UC10 – Generic Quantity with Unit Interface


- Introduced IMeasurable interface
- Created generic class Quantity
- Replaced separate classes with single generic solution
- Ensured type safety across categories
- Applied DRY principle

  
### UC11 – Volume Measurement


- Added new category: Volume
- Introduced VolumeUnit enum (L, mL, gallon)
- Supported equality, conversion, and addition
- No changes required in generic logic

  
### UC12 – Subtraction and Division


- Added subtraction and division operations
- Supported cross-unit arithmetic within same category
- Maintained immutability and consistency

  
### UC13 – Centralized Arithmetic Logic


- Refactored arithmetic logic into common method
- Removed duplicate code from operations
- Improved maintainability and readability

  
### UC14 – Temperature Measurement


- Added new category: Temperature
- Introduced units (Celsius, Fahrenheit, Kelvin)
- Supported only conversion and comparison
- Restricted invalid arithmetic operations

  
### UC15 – N-Tier Architecture Refactoring


- Refactored into layered architecture
- Introduced Controller, Service, Repository layers
- Applied SOLID principles
- Improved scalability and separation of concerns

  
### UC16 – Database Integration (JDBC)


- Integrated database using JDBC
- Replaced in-memory storage
- Implemented CRUD operations
- Enabled data persistence

  
### UC17 – Spring Boot Integration


- Converted project into Spring Boot application
- Exposed REST APIs (GET, POST, PUT, DELETE)
- Integrated Spring Data JPA
- Added Swagger and Actuator

  
### UC18 – Spring Security with OAuth2 & JWT


- Implemented authentication and authorization
- Integrated Spring Security framework
- Added JWT-based token authentication
- Implemented OAuth2 login support
- Secured REST APIs with role-based access



