# Course Management System

A secure Course Management REST API built using Spring Boot, MySQL, JWT Authentication, Role-Based Authorization, Swagger, Docker, and Docker Compose.

## Features

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

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT
- Swagger / OpenAPI
- Docker
- Docker Compose
- Maven

## API Endpoints

### Authentication APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | /auth/register | Register new user |
| POST | /auth/login | Login and generate JWT token |

### Course APIs

| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | /courses | USER / ADMIN | Get all active courses |
| GET | /courses/{id} | USER / ADMIN | Get course by id |
| GET | /courses/page | USER / ADMIN | Get courses with pagination |
| GET | /courses/search | USER / ADMIN | Search courses |
| POST | /courses | ADMIN | Create course |
| PUT | /courses/{id} | ADMIN | Update course |
| DELETE | /courses/{id} | ADMIN | Soft delete course |

## Swagger URL

After running the application, open:

http://localhost:8080/swagger-ui/index.html

## Run Locally

Clone the repository:

git clone https://github.com/kumarandsm3-lgtm/course-management-system.git

Go to project folder:

cd course-management-system

Create MySQL database:

CREATE DATABASE course_db;

Run the application:

.\mvnw spring-boot:run

## Run with Docker

Build JAR:

.\mvnw clean package -DskipTests

Build Docker image:

docker build -t coursemanagement .

Run Docker container:

docker run -d -p 8080:8080 --name course-app -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/course_db coursemanagement

## Run with Docker Compose

Docker Compose will start both Spring Boot application and MySQL database.

Start containers:

docker compose up -d --build

Check containers:

docker compose ps

Stop containers:

docker compose down

## Authentication Flow

1. Register user using /auth/register
2. Login using /auth/login
3. Copy JWT token
4. Click Authorize button in Swagger
5. Enter token like: Bearer your_jwt_token
6. Access secured APIs

## Roles

Default registered user role is USER.

To make a user ADMIN:

UPDATE users SET role = 'ADMIN' WHERE email = 'your_email@gmail.com';

## Sample Course Request

{
  "courseName": "Java Backend Development",
  "courseFee": 15000,
  "courseDuration": "3 Months",
  "courseType": "Online"
}

## Author

Kumaran K

GitHub: https://github.com/kumarandsm3-lgtm

## Project Status

Completed core backend features with JWT security, role-based authorization, Swagger, Docker, and Docker Compose support.