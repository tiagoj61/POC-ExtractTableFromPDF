# POC-ExtractTableFromPDF

## Description
**POC-ExtractTableFromPDF** is a proof-of-concept project designed to extract tabular data from PDF files using a Java batch application. The project is structured as a Maven-based Java application, enabling execution through an integrated development environment (IDE) or directly from the command line.

## Table of Contents

- [Description](#description)
- [How to Use](#how-to-use)
- [Features](#features)
- [Dependencies](#dependencies)
- [Execution](#execution)
- [Output](#output)

## How to Use

### 1. Clone the Repository
Clone the repository to your local machine:
```bash
git clone <repository-link>
```

### 2. Open in an IDE
Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans). Ensure you have Maven configured in your IDE to manage dependencies.

### 3. Execute via IDE
Run the application directly by executing the `SpringBatchApplication` class as a Java application.

### 4. Execute via Command Line
For terminal execution:

1. Compile the application:
   ```bash
   javac SpringBatchApplication.java
   ```

2. Run the application:
   ```bash
   java SpringBatchApplication
   ```

## Features

- **Batch Processing**: Processes PDFs in batches, making it efficient for large-scale data extraction.
- **Table Extraction**: Extracts tabular data from PDFs with precision.
- **Java-Based**: Built entirely in Java, ensuring portability and wide adoption.
- **Maven-Integrated**: Uses Maven for dependency management and build automation.

## Dependencies

The project relies on the following Maven dependencies:

- `org.apache.pdfbox` (for PDF manipulation)
- `org.springframework.batch` (for batch processing)
  
Ensure these dependencies are configured in your `pom.xml` file.

## Execution

### From an IDE

1. Open the `SpringBatchApplication` class.
2. Right-click and select **Run as Java Application** (specific instructions vary by IDE).

### From the Command Line

1. Compile the application:
   ```bash
   javac SpringBatchApplication.java
   ```

2. Execute the compiled program:
   ```bash
   java SpringBatchApplication
   ```
