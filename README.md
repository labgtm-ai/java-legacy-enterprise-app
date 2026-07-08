# Java Legacy Enterprise Application

## Overview

This project is a legacy Java enterprise-style Spring Boot application created for demonstrating AI-based Java modernization.

The application is intentionally developed using Java 8 and older enterprise coding practices.

The objective is to provide a working legacy application that can be analyzed by an AI Agent and modernized to Java 17+.

---

# Technology Stack

- Java 8
- Spring Boot 2.7.18
- Maven
- REST APIs
- In-memory Fake Database
- DAO-Service-Controller Architecture

---

# Application Architecture

```
Controller
    |
    v
Service
    |
    v
Mapper
    |
    v
DAO
    |
    v
FakeDatabase
```

---

# Legacy Java 8 / Older Patterns Included

The following patterns are intentionally added for modernization analysis.

| Legacy Pattern | Description | File |
|---|---|---|
| Java Bean Entities | Private fields with getters/setters | `entity/Employee.java` |
| Java Bean Entities | Private fields with getters/setters | `entity/Department.java` |
| Java Bean Entities | Address object using mutable fields | `entity/Address.java` |
| Manual DTO Mapping | Entity to DTO conversion using setters | `mapper/EmployeeMapper.java` |
| Manual DTO Mapping | Entity to DTO conversion using setters | `mapper/DepartmentMapper.java` |
| Traditional for loops | Indexed for loops instead of streams | `mapper/EmployeeMapper.java` |
| Traditional for loops | Indexed for loops instead of streams | `service/EmployeeServiceImpl.java` |
| Traditional for loops | Indexed for loops instead of streams | `service/DepartmentServiceImpl.java` |
| Null Checks | Multiple if-null validations | `mapper/EmployeeMapper.java` |
| Null Checks | Manual validation logic | `service/*Impl.java` |
| StringBuffer Usage | Legacy string concatenation | `service/EmployeeServiceImpl.java` |
| StringBuffer Usage | Legacy report generation | `service/DepartmentServiceImpl.java` |
| Vector Collection | Legacy synchronized collection | `repository/FakeDatabase.java` |
| Hashtable Collection | Legacy key-value collection | `repository/FakeDatabase.java` |
| Static Mutable State | Static in-memory storage | `repository/FakeDatabase.java` |
| Manual Object Copy | Clone/copy implementation | `mapper/EmployeeMapper.java` |
| Manual Object Copy | Clone/copy implementation | `mapper/DepartmentMapper.java` |
| Field Injection | @Autowired field injection | `controller/*Controller.java` |
| Field Injection | @Autowired field injection | `service/*Impl.java` |
| Manual ResponseEntity | Explicit HTTP response handling | `controller/*Controller.java` |
| Custom Exception Handling | Manual exception classes | `exception/ResourceNotFoundException.java` |
| Global Exception Handler | Controller advice implementation | `exception/GlobalExceptionHandler.java` |
| java.util.Date API | Legacy date handling | `entity/*` |
| Manual Object Creation | No builder pattern | `config/DataInitializer.java` |

---

# Project Structure

```
src/main/java/com/company/legacy

├── LegacyApplication.java
│
├── controller
│   ├── EmployeeController.java
│   └── DepartmentController.java
│
├── service
│   ├── EmployeeService.java
│   ├── DepartmentService.java
│   └── impl
│       ├── EmployeeServiceImpl.java
│       └── DepartmentServiceImpl.java
│
├── mapper
│   ├── EmployeeMapper.java
│   └── DepartmentMapper.java
│
├── dao
│   ├── EmployeeDAO.java
│   ├── DepartmentDAO.java
│   └── impl
│       ├── EmployeeDAOImpl.java
│       └── DepartmentDAOImpl.java
│
├── entity
│   ├── Employee.java
│   ├── Department.java
│   └── Address.java
│
├── dto
│   ├── EmployeeRequest.java
│   ├── EmployeeResponse.java
│   └── DepartmentResponse.java
│
├── repository
│   └── FakeDatabase.java
│
├── exception
│   ├── ResourceNotFoundException.java
│   └── GlobalExceptionHandler.java
│
└── config
    └── DataInitializer.java
```

---

# Running the Application

## Build Application

```bash
mvn clean install
```

## Start Application

```bash
mvn spring-boot:run
```

Application URL:

```
http://localhost:8080
```

---

# REST API Endpoints

## Employee APIs

---

## 1. Get All Employees

```
GET /api/employees
```

Example:

```
http://localhost:8080/api/employees
```

---

## 2. Get Employee By ID

```
GET /api/employees/{id}
```

Example:

```
http://localhost:8080/api/employees/1001
```

---

## 3. Search Employees

```
GET /api/employees/search/{name}
```

Example:

```
http://localhost:8080/api/employees/search/John
```

---

## 4. Employee Count

```
GET /api/employees/count
```

---

## 5. Create Employee

```
POST /api/employees
```

Request Body:

```json
{
    "employeeCode":"EMP1003",
    "firstName":"David",
    "lastName":"Williams",
    "email":"david@test.com",
    "phoneNumber":"555-1003",
    "designation":"Developer",
    "salary":85000,
    "manager":false,
    "skills":[
        "Java",
        "Spring Boot",
        "Kafka"
    ]
}
```

---

## 6. Update Employee

```
PUT /api/employees/{id}
```

Example:

```
PUT /api/employees/1001
```

---

## 7. Delete Employee

```
DELETE /api/employees/{id}
```

Example:

```
DELETE /api/employees/1001
```

---

# Department APIs

---

## 1. Get All Departments

```
GET /api/departments
```

---

## 2. Get Department By ID

```
GET /api/departments/{id}
```

Example:

```
GET /api/departments/1
```

---

## 3. Search Departments

```
GET /api/departments/search/{name}
```

Example:

```
GET /api/departments/search/Engineering
```

---

## 4. Department Count

```
GET /api/departments/count
```

---

## 5. Create Department

```
POST /api/departments
```

---

## 6. Update Department

```
PUT /api/departments/{id}
```

---

## 7. Delete Department

```
DELETE /api/departments/{id}
```

---

# AI Modernization Agent Target

The AI Agent should identify and modernize:

| Legacy Code | Modern Java 17 Approach |
|---|---|
| Java 8 loops | Streams / enhanced APIs |
| Date | java.time API |
| Mutable POJOs | Records where applicable |
| Field Injection | Constructor Injection |
| Vector | List / Concurrent Collections |
| Hashtable | Map / ConcurrentHashMap |
| StringBuffer | StringBuilder / String formatting |
| Manual Mapping | MapStruct / cleaner mapper |
| Manual Exception Handling | Modern REST error handling |
| Traditional Threads | CompletableFuture / Virtual Threads |
| Old Spring patterns | Spring Boot 3.x patterns |

---

# Purpose

This repository represents the **before modernization state**.

The AI modernization workflow:

```
Legacy Java 8 Repository

        |
        v

Repository Scanner

        |
        v

AST Analyzer

        |
        v

RAG Knowledge Retrieval

        |
        v

AI Code Modernization

        |
        v

Java 17 Modern Application
```