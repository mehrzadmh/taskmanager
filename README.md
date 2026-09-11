# Task Manager API

A RESTful Task Management application built with Spring Boot following a layered architecture.

## 🚀 Features

- User Registration & Login
- JWT Authentication
- Role-Based Authorization
- Task CRUD Operations
- Pagination & Sorting
- Dynamic Search using Spring Specifications
- Input Validation
- Global Exception Handling
- Swagger/OpenAPI Documentation
- Docker Support

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- Oracle Database
- Docker
- Maven
- Swagger (OpenAPI)

---

## 📁 Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── mapper
 ├── security
 ├── exception
 └── configuration
```

---

## 🔐 Authentication

The project uses JWT (JSON Web Token) authentication.

Protected endpoints require:

```
Authorization: Bearer <your_token>
```

---

## 🐳 Docker

Build Docker Image

```bash
docker build -t taskmanager .
```

Run Container

```bash
docker run -d -p 8080:8080 taskmanager
```

---

## 📖 API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📌 Status

This project is under active development and is continuously being improved.