# Employee Tracking System

## Overview
The Employee Tracking System is a Spring Boot-based RESTful API for managing employees, departments, and projects within an organization. It includes role-based access control (RBAC) and caching for improved performance.

---

## Features
1. **Department Management**
    - View, add, update, and delete departments.
    - Retrieve projects by department.

2. **Employee Management**
    - View, add, update, and delete employees.
    - Search employees by name, email, or department.

3. **Project Management**
    - View, add, update, and delete projects.

4. **Role-Based Access Control (RBAC)**
    - Restrict API access based on user roles (ADMIN, MANAGER).

5. **Caching**
    - Cache employee data and search results for better performance.

---

## API Endpoints

### **Department APIs**
| HTTP Method | Endpoint                | Description                              | Access Role         |
|-------------|-------------------------|------------------------------------------|---------------------|
| GET         | `/departments`          | Get all departments                      | ADMIN, MANAGER      |
| GET         | `/departments/{id}`     | Get a department by ID                   | ADMIN, MANAGER      |
| POST        | `/departments`          | Add a new department                     | ADMIN               |
| PUT         | `/departments/{id}`     | Update an existing department            | ADMIN               |
| DELETE      | `/departments/{id}`     | Delete a department                      | ADMIN               |
| GET         | `/departments/{id}/projects` | Get projects in a department            | ADMIN, MANAGER      |

---

### **Employee APIs**
| HTTP Method | Endpoint                | Description                              | Access Role                                   |
|-------------|-------------------------|------------------------------------------|---------------------------------------------|
| GET         | `/employees`            | Get all employees                        | ADMIN, MANAGER                              |
| GET         | `/employees/{id}`       | Get an employee by ID                    | ADMIN, MANAGER (with restrictions)          |
| POST        | `/employees`            | Add a new employee                       | ADMIN                                       |
| PUT         | `/employees/{id}`       | Update an existing employee              | ADMIN                                       |
| DELETE      | `/employees/{id}`       | Delete an employee                       | ADMIN                                       |
| GET         | `/employees/search`     | Search employees                         | ADMIN, MANAGER                              |

---

### **Project APIs**
| HTTP Method | Endpoint                | Description                              | Access Role         |
|-------------|-------------------------|------------------------------------------|---------------------|
| GET         | `/projects`             | Get all projects                         | ADMIN, MANAGER      |
| GET         | `/projects/{id}`        | Get a project by ID                      | ADMIN, MANAGER      |
| POST        | `/projects`             | Add a new project                        | ADMIN               |
| PUT         | `/projects/{id}`        | Update an existing project               | ADMIN               |
| DELETE      | `/projects/{id}`        | Delete a project                         | ADMIN               |

---

## Role-Based Access Control
- **ADMIN**: Full access to all APIs.
- **MANAGER**: Limited access based on department and employee restrictions.

---

## Caching
- **Caching Implementation**: Uses Spring Cache.
- Cached Entities:
    - All employees.
    - Individual employees by ID.
    - Employee search results.

---

## Prerequisites
1. Java 17+
2. Gradle
3. Spring Boot 3+
4. Database (e.g., MySQL or PostgreSQL)
5. Spring Security for RBAC
6. Spring Cache and Redis server for caching.

---

## How to Run
1. Clone the repository.
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory.
   ```bash
   cd employee-tracking-system
   ```
3. Update the `application.properties` file with your database credentials.
4. Build the project.
   ```bash
   ./mvn clean install
   ```
5. Run the application.

---

## Tools and Technologies
- **Spring Boot**: Backend framework.
- **Spring Security**: Role-based API access.
- **Spring Cache**: Optimized performance.
- **Hibernate/JPA**: Database interactions.
