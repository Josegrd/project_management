# Project Management API - Backend Developer Technical Test

Welcome to the **Project Management API** repository! This project is the backend solution for a project management system, developed as part of a technical test. Built using **Java Spring Boot** and integrated with **PostgreSQL**, the API supports project management (CRUD), authentication via **JWT**, and file uploads (document, image, and video).

---

## 🛠️ Table of Contents
- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Installation Guide](#installation-guide)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [More Info](#more-info)

---

## 🌟 Project Overview

This API facilitates project management with support for basic authentication via **JWT**, CRUD operations for managing projects, and file uploads for associated documents (PDF, DOCX), images (PNG, JPG, JPEG), and videos (MP4, MOV, AVI).

The application focuses on:
- **Authentication**: Uses **JWT (JSON Web Token)** for secure access.
- **CRUD Operations**: Create, read, update, and delete projects.
- **File Management**: Supports uploading and replacing files for projects.
- **Database**: PostgreSQL is used to store project data.

**Note:** The database, `project_management_db`, must be created before running the application.

---

## 🚀 Technologies Used

- **Java**: Core programming language for backend development.
- **Spring Boot**: For building RESTful APIs and managing application dependencies.
- **PostgreSQL**: Relational database used for storing project and user data.
- **JWT**: Provides secure authentication using JSON Web Tokens.
- **Swagger**: Provides an interactive API documentation interface.
- **Spring Security**: For implementing secure authentication mechanisms.

---

## 🔑 Features

### 1. **Authentication**
- **JWT Authentication**: Secure login using JWT for API access.
    - **Credentials**:
        - Username: `admin`
        - Password: `admin123`
    - **Login Endpoint**: `POST /login` generates a JWT token.

### 2. **CRUD Operations for Projects**
- **Create Project**: Create a project with 10 fields (e.g., name, description, etc.).
- **Read Project**: Retrieve details of a specific project.
- **Update Project**: Modify an existing project.
- **Delete Project**: Delete a project.

### 3. **File Upload and Management**
- **Supported File Types**:
    - **Documents**: PDF, DOCX files.
    - **Images**: PNG, JPG, JPEG files.
    - **Videos**: MP4, MOV, AVI files.
- **File Editing**: Replace existing files (image, video, document) for a project.

### 4. **Database Configuration**
- **PostgreSQL**: The application requires a PostgreSQL database named `project_management_db`.
- **Database Setup**:
    - Create the database with `CREATE DATABASE project_management_db;`.

### 5. **API Documentation**
- The API is documented using **Swagger** for easy testing and exploration.
- Access the documentation at:  
  [Swagger UI - API Documentation](http://localhost:8082/swagger-ui/index.html)

---

## 📦 Installation Guide

### 1. Clone the Repository

Clone the repository using Git:

```bash
git clone https://github.com/Josegrd/project-management.git
```
### 2. Set Up the Database
Create the database project_management_db in PostgreSQL:

sql
```bash
CREATE DATABASE project_management_db;
```
### 3. Configure Database Connection
In the application.properties file, configure the database connection details:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/project_management_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

### 4. Configure JWT Secret
In the application.properties file, configure the JWT secret and expiration:

```bash
jwt.secret=your_jwt_secret_key
jwt.expiration=3600 # Token expiration in seconds (1 hour)
```

### 5. Build the Application
Run Maven to build the project:

```bash
mvn clean install
```

### 6. Run the Application
After building, you can run the application:

```bash
mvn spring-boot:run
```

The application will run on port 8082.


## 🔐 Authentication
Login
To get a JWT token, send a POST request to /login with the following JSON body:

json
```bash
{
  "username": "admin",
  "password": "admin123"
}
```

The response will include the JWT token:

json
```bash
{
  "token": "your_jwt_token_here"
}
```
### Authorization
To access protected endpoints, include the JWT token in the Authorization header as follows:

makefile
```bash
Authorization: Bearer your_jwt_token_here
```

## 🔑 API Documentation
Once the application is running, you can explore and test the API via Swagger at:

[Swagger UI - API Documentation](http://localhost:8082/swagger-ui/index.html)
or
```bash
http://localhost:8082/swagger-ui/index.html
port 8082 is the default
```

📂 Project Structure
Here's an overview of the project's directory structure:

```bash
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── sm/
│   │   │           └── technical_test/
│   │   │               ├── controller/
│   │   │               ├── service/
│   │   │               ├── entity/
│   │   │               ├── repository/
│   │   │               └── util/
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   └── static/
│   └── test/
├── pom.xml
```

## 📝 More Info
For updates and questions, feel free to reach out through the following:
- Twitter/X: @jsgrd8
- GitHub: Josegrd - Project Management


## ❓ FAQs
### 1. How to set up the database?
 - Before running the application, create the project_management_db database in PostgreSQL.
### 2. How do I test the API?
 - You can use Swagger to easily test the endpoints directly in the browser.
### 3. Can I register a new user?
 - Currently, only the predefined admin user is available (username: admin, password: admin123).
### 4. What if I encounter issues during file upload?
 - Ensure the files are within allowed sizes and types (PDF, DOCX, PNG, JPG, MP4, MOV, AVI).
