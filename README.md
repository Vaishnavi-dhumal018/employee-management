# Employee Management System – Spring Boot REST API

A REST API for managing employees and departments, built with **Spring Boot**, **Spring Data JPA**, and **MySQL**. Follows a layered architecture (Controller → Service → Repository) with centralized exception handling and request validation.

## Tech Stack
- Java 17
- Spring Boot 3.2 (Spring Web, Spring Data JPA, Validation)
- MySQL
- Maven
- Lombok

## Features
- Full CRUD for Employees and Departments
- Filter employees by department, designation, or minimum salary
- Bean validation on incoming requests (`@NotBlank`, `@Email`, `@Positive`)
- Centralized exception handling via `@RestControllerAdvice`
- Custom exceptions: `EmployeeNotFoundException`, `DepartmentNotFoundException`, `DuplicateEmailException`
- Clean separation of concerns across Controller, Service, and Repository layers

## Project Structure
```
src/main/java/com/vaishnavi/employeemanagement/
├── controller/     # REST endpoints
├── service/        # Business logic
├── repository/     # Spring Data JPA repositories
├── model/          # JPA entities (Employee, Department)
└── exception/      # Custom exceptions + global handler
```

## Getting Started

### Prerequisites
- JDK 17+
- Maven 3.6+
- MySQL running locally

### Setup
1. Create a MySQL database (or let the app auto-create it):
   ```sql
   CREATE DATABASE employee_management_db;
   ```
2. Update `src/main/resources/application.properties` with your MySQL username/password.
3. Run the app:
   ```bash
   mvn spring-boot:run
   ```
4. The API will be available at `http://localhost:8080`.

## API Endpoints

### Departments
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/departments` | Create a department |
| GET | `/api/departments` | List all departments |
| GET | `/api/departments/{id}` | Get department by id |
| DELETE | `/api/departments/{id}` | Delete department |

### Employees
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/employees?departmentId={id}` | Create an employee under a department |
| GET | `/api/employees` | List all employees |
| GET | `/api/employees/{id}` | Get employee by id |
| PUT | `/api/employees/{id}` | Update an employee |
| DELETE | `/api/employees/{id}` | Delete an employee |
| GET | `/api/employees/department/{departmentId}` | List employees in a department |
| GET | `/api/employees/designation/{designation}` | List employees by designation |
| GET | `/api/employees/search?minSalary={amount}` | List employees earning at least `minSalary` |

## Sample Request

**Create a department**
```http
POST /api/departments
Content-Type: application/json

{
  "name": "Engineering"
}
```

**Create an employee**
```http
POST /api/employees?departmentId=1
Content-Type: application/json

{
  "name": "Vaishnavi Dhumal",
  "email": "vaishnavi@example.com",
  "phone": "9307860584",
  "designation": "Software Engineer",
  "salary": 45000,
  "joiningDate": "2026-07-01"
}
```

## Author
Vaishnavi Dhumal
