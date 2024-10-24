# Hatio Assignment - Todo App

![Spring Boot Badge](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff&style=flat-square)
![Spring Security Badge](https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity&logoColor=fff&style=flat-square)
![Spring Badge](https://img.shields.io/badge/Spring-6DB33F?logo=spring&logoColor=fff&style=flat-square)
![OpenJDK Badge](https://img.shields.io/badge/OpenJDK-000?logo=openjdk&logoColor=fff&style=flat-square)
![PostgreSQL Badge](https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=fff&style=flat-square)
![Docker Badge](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff&style=flat-square)

## Overview

This project is a backend service built using `Spring Boot` and `Java`. It exposes RESTful API endpoints for managing
resources in a `PostgreSQL database`. The application is containerized using `Docker`, enabling easy deployment and
scaling.

For Security of endpoints and app, This project implements `spring-security`. All endpoints (if required) are secured to
be accessed by only authentic users.

### Documentation

[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://documenter.getpostman.com/view/38347451/2sAY4rGQwu)

### Project Dependencies

- **Java**: `opne jdk: v22.0`.
- **Spring Boot**: `v3.3.3`.
- **PostgreSQL**: `v16.0`.
- **Docker**: with `docker-compose v3.0`.
- **Maven**: `v4.0.0`.

### Features

- RESTful API endpoints for CRUD operations.
- User authentication and authorization.
- Integration with PostgreSQL for persistent storage.
- Dockerized application for easy deployment.

## Getting Started

### 1. Clone this repository

```shell
https://github.com/abhijeet-Bh/hatio-assignment.git
```

### 2. Build the application

```shell
./mvnn clean install
```

### 3. Running the Application

You can run the application using `Maven` or `Docker`.

#### A. Running with Maven:

1. **Download & Install PostgreSQL database**

2. **Connect to the PostgreSQL database:**

   Ensure PostgreSQL is running and configured as per `application.properties`.

   ```.properties
   spring.application.name=app
   spring.datasource.url=jdbc:postgresql://localhost:5432/<your-database-name>
   spring.datasource.username=<your-db-usename>
   spring.datasource.password=<your-password>
   spring.datasource.driver-class-name=org.postgresql.Driver

   ## Hibernate (JPA) Properties
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

3. **Run the Spring Boot application:**

   ```shell
   ./mvnw spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

---

#### B. Running with Docker:

1. **Build and run the Docker containers:**

   To run with docker-compose, you just need to setup `.env` file in the root directory as given below

   ```.env
   # PostgreSQL database configuration for Docker
   POSTGRES_DB=<your-db-name>
   POSTGRES_USER=<your-db-user>
   POSTGRES_PASSWORD=<your-db-password>
   SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/<your-db>
   SPRING_DATASOURCE_USERNAME=<your-db-name>
   SPRING_DATASOURCE_PASSWORD=<your-db-user>
   SPRING_JPA_HIBERNATE_DDL_AUTO=update
   ```

   > _Now, you can run below command to run `Todo` app :)_

   ```bash
   docker-compose up --build
   ```

This command will start the Spring Boot application and PostgreSQL database in Docker containers.

## API Documentation

### Endpoints

Here’s a list of all **API endpoints** for the **Todo App Backend**. These endpoints cover the main functionalities
for managing projects, todos, exporting project-summary as gist or downloading it.

### **1. Public Endpoints**

These endpoints handle `public-related` operations such as `health-check` and `login`.

| HTTP Method | Endpoint          | Description                         | Access Role |
| ----------- | ----------------- | ----------------------------------- | ----------- |
| **GET**     | `/api/v1/healthz` | Check running status of the backend | OPEN        |

### **2. Projects Endpoints**

These endpoints handle `project-related` operations for managing the project.

| HTTP Method | Endpoint                | Description          | Access Role |
| ----------- | ----------------------- | -------------------- | ----------- |
| **GET**     | `/api/v1/projects`      | Get All Projects     | OPEN        |
| **POST**    | `/api/v1/projects`      | Create New Project   | OPEN        |
| **PUT**     | `/api/v1/projects`      | Update New Project   | OPEN        |
| **GET**     | `/api/v1/projects/{id}` | Get Project by Id    | OPEN        |
| **DELETE**  | `/api/v1/projects/{id}` | Delete Project by Id | OPEN        |

### **3. Todo Endpoints**

These endpoints handle `Todo-related` operations for managing the project.

| HTTP Method | Endpoint                                                 | Description                                | Access Role |
| ----------- | -------------------------------------------------------- | ------------------------------------------ | ----------- |
| **GET**     | `/api/v1/projects/{project-Id}/todos`                    | Get All Todos of the particular project    | OPEN        |
| **POST**    | `/api/v1/projects/{project-Id}/todos`                    | Create New todo for the particular project | OPEN        |
| **PATCH**   | `/api/v1/projects/{project-Id}/todos/{todo-Id}/complete` | Mark a todo complete                       | OPEN        |
| **PATCH**   | `/api/v1/projects/{project-Id}/todos/{todo-Id}/pending`  | Mark a todo pending                        | OPEN        |
| **PUT**     | `/api/v1/projects/{project-Id}/todos/{todo-Id}`          | Update Todo                                | OPEN        |
| **DELETE**  | `/api/v1/projects/{project-Id}/todos/{todo-Id}`          | Delete Todo by Id                          | OPEN        |

### **4. Gists Endpoints**

These endpoints handle `gist-related` operations for downloading and exporting the the `project-summary`.

| HTTP Method | Endpoint                                                           | Description                          | Access Role |
| ----------- | ------------------------------------------------------------------ | ------------------------------------ | ----------- |
| **GET**     | `/api/v1/projects/{project-Id}/download-summary`                   | Download project-summary             | OPEN        |
| **POST**    | `/api/v1/projects/{project-Id}/export-gist?githubToken=your-token` | Export Project Summary to giHub gist | OPEN        |

> Gist that i created while developing this project is [here](https://gist.github.com/abhijeet-Bh/8caffb872bcecb51b6115bb765ababb7)

Thanks :)
