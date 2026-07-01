# Course Management System

A full-stack Course Management Application built using Spring Boot, MySQL, JWT Authentication, Role-Based Authorization, Swagger, Docker, Docker Compose, React, JUnit, and Mockito.

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
- Unit Testing with JUnit and Mockito
- Service Layer Test Coverage

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

### Testing

- JUnit 5
- Mockito
- Maven Surefire Plugin

### DevOps

- Docker
- Docker Compose
- GitHub

## Project Structure

```text
coursemanagement
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.kumaran.coursemanagement
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── entity
│   │   │       ├── exception
│   │   │       ├── mapper
│   │   │       ├── repository
│   │   │       ├── response
│   │   │       ├── security
│   │   │       └── service
│   │   └── resources
│   └── test
│       └── java
│           └── com.kumaran.coursemanagement
│               └── service
│                   └── impl
│                       └── CourseServiceImplTest.java
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
```

## API Endpoints

### Authentication APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register new user |
| POST | `/auth/login` | Login and generate JWT token |

### Course APIs

| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/courses` | USER / ADMIN | Get all active courses |
| GET | `/courses/{id}` | USER / ADMIN | Get course by id |
| GET | `/courses/page` | USER / ADMIN | Get courses with pagination |
| GET | `/courses/search` | USER / ADMIN | Search courses |
| POST | `/courses` | ADMIN | Create course |
| PUT | `/courses/{id}` | ADMIN | Update course |
| DELETE | `/courses/{id}` | ADMIN | Soft delete course |

## Swagger URL

After running the backend, open:

```text
http://localhost:8080/swagger-ui/index.html
```

## Frontend URL

After running the React frontend, open:

```text
http://localhost:5173
```

## Run Backend with Docker Compose

Docker Compose will start both:

- Spring Boot backend container
- MySQL database container

```powershell
docker compose up -d --build
```

Check containers:

```powershell
docker compose ps
```

Stop containers:

```powershell
docker compose down
```

## Run Frontend

Go to React project folder:

```powershell
cd course-ui
```

Install dependencies:

```powershell
npm install
```

Run React frontend:

```powershell
npm run dev
```

Open:

```text
http://localhost:5173
```

## Run Frontend Production Build

Go to React project folder:

```powershell
cd course-ui
```

Create production build:

```powershell
npm run build
```

## Run Unit Tests

This project includes unit tests for the Course Service layer using JUnit and Mockito.

Run tests:

```powershell
.\mvnw test
```

Covered test scenarios:

- Create course
- Get course by id
- Get all active courses
- Course not found exception
- Soft delete course

Test file location:

```text
src/test/java/com/kumaran/coursemanagement/service/impl/CourseServiceImplTest.java
```

## Authentication Flow

1. User registers using React Register Page or `/auth/register`
2. User logs in using React Login Page or `/auth/login`
3. Backend validates credentials
4. Backend generates JWT token
5. React stores JWT token in localStorage
6. React sends JWT token in Authorization header
7. Backend validates token using JWT filter
8. Protected APIs are accessed based on user role

## Roles

Default registered user role is:

```text
USER
```

USER can:

```text
View courses
Search courses
```

ADMIN can:

```text
View courses
Create courses
Update courses
Delete courses
Search courses
```

To make a user ADMIN inside Docker MySQL:

```powershell
docker exec -i course-mysql mysql -uroot -p1234 course_db -e "UPDATE users SET role='ADMIN' WHERE email='your_email@gmail.com'; SELECT id, name, email, role FROM users;"
```

## Sample Course Request

```json
{
  "courseName": "Java Backend Development",
  "courseFee": 15000.0,
  "courseDuration": 4,
  "courseType": "Online"
}
```

## Docker Notes

Safe restart:

```powershell
docker compose down
docker compose up -d --build
```

Do not use this unless you want to delete database data:

```powershell
docker compose down -v
```

## Completed Work

- Backend REST API completed
- User registration and login completed
- JWT Authentication completed
- Role-Based Authorization completed
- DTO pattern implemented
- Mapper layer implemented
- Global exception handling implemented
- Course CRUD APIs completed
- Course search completed
- Pagination and sorting completed
- Soft delete implemented
- Swagger documentation completed
- Dockerfile added
- Docker Compose with MySQL added
- React frontend added
- Login and Register UI integrated with backend
- Dashboard integrated with real backend APIs
- Course Add, Edit, Delete working from React UI
- JUnit and Mockito unit tests added for Course Service layer
- React production build verified
- Project pushed to GitHub

## Author

Kumaran K

GitHub: https://github.com/kumarandsm3-lgtm

## Project Status

Full-stack Course Management System completed with Spring Boot backend, React frontend, MySQL database, JWT security, role-based authorization, Docker Compose, Swagger documentation, and JUnit/Mockito unit testing.