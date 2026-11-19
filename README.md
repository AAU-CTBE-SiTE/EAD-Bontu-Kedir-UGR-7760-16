# Student Management REST API

## 1. Project Overview

This project implements a RESTful Student Management API using Spring Boot, Spring Data JPA, and MySQL.

The application allows users to create, retrieve, update, and delete student records through HTTP requests. It follows a layered architecture to separate responsibilities and improve maintainability.

## 2. Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman

## 3. Project Architecture

The application follows a layered architecture consisting of four main layers:

### Model
Defines the `Student` entity and its mapping to the database table. It represents the student data managed by the application.

### Repository
Uses `JpaRepository` to provide database operations. Spring Data JPA automatically provides the implementation of the repository interface, reducing the need to write SQL queries manually.

### Service
Handles the application's business logic and coordinates operations between the controller and repository layers.

### Controller
Exposes REST API endpoints and handles incoming HTTP requests. It communicates with the service layer and returns appropriate responses.

## 4. Database Configuration

- **Database:** student_management
- **Database Management System:** MySQL
- **Configuration File:** `application.properties`

The database connection is configured through Spring Boot application properties. Database credentials are kept separate and are not included in the repository.

## 5. Student Attributes

The Student entity contains the following attributes:

| Attribute | Description |
|---|---|
| ID | Automatically generated unique identifier |
| First Name | Student's first name |
| Last Name | Student's last name |
| Email | Student's email address |
| Department | Student's academic department |

### Additional Feature: Email Validation

Email validation was added to ensure that the email field is required and contains a valid email address.

## 6. Implemented CRUD Operations

The application supports the following REST API operations:

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/api/students` | Create a new student |
| GET | `/api/students` | Retrieve all students |
| GET | `/api/students/{id}` | Retrieve a student by ID |
| PUT | `/api/students/{id}` | Update an existing student |
| DELETE | `/api/students/{id}` | Delete a student by ID |

## 7. Running the Application

### Prerequisites

- Java Development Kit (JDK)
- Maven
- MySQL Server

### Steps

1. Clone the repository.
2. Open the project in an IDE such as IntelliJ IDEA.
3. Create the MySQL database named `student_db`.
4. Configure the database connection and credentials in your local `application.properties` file.
5. Run the Spring Boot application.
6. Use Postman to test the REST API endpoints.

The application runs on the default port `8081`, unless configured otherwise.

## 8. Testing and Verification

Postman was used to test the implemented REST API endpoints.

The following operations were tested:

- Creating a student using POST.
- Retrieving all students using GET.
- Retrieving an individual student by ID.
- Updating student information using PUT.
- Deleting a student using DELETE.

The API responses were checked to verify that the operations worked successfully.

## 9. Troubleshooting

During development, some common issues were encountered:

### Database Connection and Authentication Errors

**Cause:** Incorrect database credentials or a mismatch between the configured password and the MySQL password.

**Solution:** Checked the database configuration in `application.properties` and verified the MySQL connection settings.

### Application and API Errors

**Cause:** Incorrect endpoint URLs, application startup problems, or configuration issues.

**Solution:** Examined application logs, checked the relevant configuration and implementation, and verified the API requests using Postman.

## 10. Learning Outcomes

Through this laboratory exercise, I practiced:

- Understanding RESTful APIs and HTTP methods.
- Configuring a Spring Boot Maven project.
- Connecting a Spring Boot application to MySQL.
- Understanding JPA entities and Spring Data repositories.
- Implementing layered architecture.
- Using dependency injection.
- Understanding how Hibernate works with JPA.
- Implementing CRUD operations.
- Adding email validation.
- Testing REST API endpoints using Postman.
- Troubleshooting application and database errors.

## 11. AI-Assisted Learning

ChatGPT was used as a learning assistant throughout the laboratory sessions to explain concepts, guide implementation step by step, interpret errors, and support troubleshooting.

The implementation was tested and verified during the practical sessions. The AI interaction history is submitted separately as evidence of the learning process.

## AI Interaction History

The following ChatGPT conversation documents the AI-assisted learning process, including Spring Boot implementation guidance, explanations, troubleshooting, and testing.

[View ChatGPT Interaction History](https://chatgpt.com/c/6afea088-7bf8-83e9-b012-a9a070a31f5d)
https://chatgpt.com/c/6afea741-f0fc-83ea-baac-e2e0700fba68
