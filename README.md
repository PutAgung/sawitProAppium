# Automation Assessment

Appium test for completing the Assessment

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [How to Run](#how-to-run)
- [Project Structure](#project-structure)

## Prerequisites

- Java Development Kit (JDK)
- Maven
- Appium and its dependency, check here: https://appium.io/docs/en/2.2/quickstart/install/

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/PutAgung/sawitProAppium.git
   ```
2. Navigate to the project repository
   ```bash
   cd sawitProAppium
   ```
3. Install dependencies and compile the code:
   ```bash
   mvn compile
   ```

## How to Run
Run the program using Maven:
   ```bash
   mvn exec:java -Dexec.mainClass=AppiumTest.LoginPageTest
   ```

## Project Structure
   ```bash
src/
|-- test/
|   `-- java/
|       `-- your/
|           `-- package/
|               `-- LoginPageTest.java
`-- pom.xml
target/
|-- extent-report.html
  ```

