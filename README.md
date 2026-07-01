# Course Management System

A full-stack Course Management Application built using Spring Boot, MySQL, JWT Authentication, Role-Based Authorization, Swagger, Docker, Docker Compose, and React.

## Features

### Backend Features

- User Registration and Login
- JWT Authentication
- Role-Based Authorization
  - USER can view courses
  - ADMIN can create, update, and delete courses
- Course CRUD APIs
- DTO Pattern
- Mapper Layer
- Validation
- Global Exception Handling
- Pagination and Sorting
- Course Search
- Soft Delete
- Swagger API Documentation
- Docker Support
- Docker Compose with MySQL

### Frontend Features

- React + Vite Frontend
- Apple-inspired premium UI
- Landing Page
- Login Page
- Register Page
- Dashboard Page
- JWT token stored in localStorage
- Course List from Backend API
- Add Course from UI
- Edit Course from UI
- Delete Course from UI
- Search Courses in Dashboard
- Logout Functionality

## Tech Stack

### Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT
- Swagger / OpenAPI
- Maven

### Frontend

- React
- Vite
- JavaScript
- HTML
- CSS
- React Router DOM

### DevOps

- Docker
- Docker Compose
- GitHub

## Project Structure

```text
coursemanagement
├── src
│   └── main
│       ├── java
│       │   └── com.kumaran.coursemanagement
│       │       ├── config
│       │       ├── controller
│       │       ├── dto
│       │       ├── entity
│       │       ├── exception
│       │       ├── mapper
│       │       ├── repository
│       │       ├── response
│       │       ├── security
│       │       └── service
│       └── resources
├── course-ui
│   ├── src
│   │   ├── pages
│   │   │   ├── Login.jsx
│   │   │   ├── Register.jsx
│   │   │   └── Dashboard.jsx
│   │   ├── App.jsx
│   │   ├── App.css
│   │   └── main.jsx
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md