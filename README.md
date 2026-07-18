# Java Modernization Demo

## Overview

This repository contains a lightweight Spring Boot microservice intentionally written using Java 7/8-era coding patterns.

It is designed as a fast, easy-to-understand input repository for the **SRAO Java Modernization Agent**.

The agent analyzes the source code, identifies legacy patterns, modernizes the code for a selected Java target version, validates the updated project, and creates a GitHub pull request.

Supported modernization targets include:

- Java 14
- Java 17
- Java 21

This repository is intentionally small so the complete modernization workflow can be demonstrated quickly during a live presentation.

---

## Project Purpose

The project demonstrates the following end-to-end workflow:

```text
Git Repository
      |
      v
Repository Scanner
      |
      v
Legacy Pattern Detection
      |
      v
RAG-Based Migration Guidance
      |
      v
AI Code Modernization
      |
      v
Maven Compilation Validation
      |
      v
Spring Boot Startup Validation
      |
      v
GitHub Pull Request
```

---

## Technology Stack

| Component | Value |
|---|---|
| Java baseline | Java 8 compilation |
| Coding style | Java 7/8-era legacy patterns |
| Framework | Spring Boot 2.7.18 |
| Build tool | Maven |
| API | Spring MVC REST |
| Testing | JUnit |

> The project compiles initially using Java 8 because Spring Boot 2.7 requires Java 8 or later. The source code intentionally uses older Java programming styles to provide clear modernization opportunities.

---

## Project Structure

```text
java-modernization-demo
│
├── pom.xml
├── README.md
│
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── demo
    │   │           └── legacy
    │   │               ├── LegacyDemoApplication.java
    │   │               ├── OrderController.java
    │   │               └── OrderService.java
    │   │
    │   └── resources
    │       └── application.properties
    │
    └── test
        └── java
            └── com
                └── demo
                    └── legacy
                        └── OrderServiceTest.java
```

---

## Application Flow

```text
REST Request
     |
     v
OrderController
     |
     v
OrderService
     |
     v
Legacy Processing Logic
```

---

## Java Files

| File | Responsibility |
|---|---|
| `LegacyDemoApplication.java` | Starts the Spring Boot application |
| `OrderController.java` | Exposes the REST endpoint |
| `OrderService.java` | Contains business logic and intentionally added legacy Java patterns |
| `OrderServiceTest.java` | Tests the service behavior |

---

## Legacy Patterns Included

The following patterns are intentionally included in the project.

| Legacy Pattern | File |
|---|---|
| Anonymous inner class | `OrderService.java` |
| Thread | `OrderService.java` |
| Runnable | `OrderService.java` |
| synchronized method | `OrderService.java` |
| Blocking call using `Thread.sleep()` | `OrderService.java` |
| Callback interface | `OrderService.java` |
| Raw collection type | `OrderService.java` |
| Unchecked cast | `OrderService.java` |
| Traditional loop | `OrderService.java` |
| Explicit null check | `OrderService.java` |
| StringBuffer | `OrderService.java` |
| String concatenation | `OrderService.java` |
| Traditional switch statement | `OrderService.java` |
| Field injection | `OrderController.java` |

---

## Modernization Opportunities

| Legacy Pattern | Java 14 Target | Java 17 Target | Java 21 Target |
|---|---|---|---|
| Anonymous inner class | Lambda expression | Lambda expression | Lambda expression |
| Raw collection | Generic collection | Generic collection | Generic collection |
| Manual loop | Stream API | Stream API | Stream API |
| StringBuffer | StringBuilder | StringBuilder | Modern string APIs |
| Traditional switch | Switch expression | Switch expression | Pattern matching for switch where applicable |
| Explicit null check | Optional or defensive validation | Optional or improved null handling | Modern null-safe flow |
| Thread and Runnable | ExecutorService or CompletableFuture | CompletableFuture | Virtual threads |
| Blocking call | CompletableFuture or executor task | CompletableFuture | Virtual-thread-compatible blocking |
| Callback interface | CompletableFuture | CompletableFuture | CompletableFuture or structured concurrency |
| Field injection | Constructor injection | Constructor injection | Constructor injection |

> The actual modernization depends on the target Java version selected when running the SRAO Agent.

---

## REST Endpoint

### Generate Order Summary

```http
GET /api/orders/summary
```

### Query Parameters

| Parameter | Required | Example |
|---|---|---|
| `customer` | No | `John` |
| `status` | No | `NEW` |

### Example Request

```text
http://localhost:8080/api/orders/summary?customer=John&status=NEW
```

### Example Response

```text
Customer: John | Status: NEW | Items: BOOK,LAPTOP | Processed: true
```

---

## Running the Application

### Build

```bash
mvn clean compile
```

### Run Tests

```bash
mvn test
```

### Start the Application

```bash
mvn spring-boot:run
```

The application starts at:

```text
http://localhost:8080
```

---

## Testing the Endpoint

### Browser or Postman

```text
GET http://localhost:8080/api/orders/summary?customer=John&status=NEW
```

### Curl

```bash
curl "http://localhost:8080/api/orders/summary?customer=John&status=NEW"
```

---

## AI Modernization Workflow

The SRAO Agent accepts:

```text
Repository URL
Target Java Version
```

Example:

```text
Repository:
https://github.com/<owner>/java-modernization-demo

Target:
Java 17
```

The agent performs:

1. Clone the repository.
2. Find Java source files.
3. Detect supported legacy patterns.
4. Filter patterns based on the selected Java target.
5. Retrieve migration guidance.
6. Modernize the source code.
7. Update the Maven Java version.
8. Compile the project.
9. Run the Spring Boot application.
10. Create a feature branch.
11. Commit validated changes.
12. Create a GitHub pull request.

---

## Expected Demo Outcome

The generated pull request should contain:

- Modernized Java source code
- Updated `pom.xml`
- Pattern details
- Severity summary
- Target Java version
- Maven validation results
- Spring Boot startup validation
- File-level modernization details

---

## Demo Strategy

This repository is intended for the live modernization demonstration because it is small and processes quickly.

Larger repositories can be prepared before the demo and shared separately to demonstrate that the same agent can handle more realistic enterprise applications.

```text
Live Demo Repository
- 3 production Java files
- 1 test file
- Fast analysis
- Fast modernization
- Fast PR generation

Enterprise Sample Repositories
- Larger codebase
- More architectural layers
- More modernization patterns
- Preprocessed before the presentation
```

---

## Important Note

The legacy patterns in this project are intentional.

This repository should not be treated as an example of modern Java development. Its purpose is to provide a compact and realistic source repository for demonstrating automated Java modernization.

---

## License

This project is intended for educational, demonstration, and research purposes.