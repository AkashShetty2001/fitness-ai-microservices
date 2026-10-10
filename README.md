# Fitness Microservices Application

A microservices-based fitness application built with Spring Boot, demonstrating modern backend architecture and best practices.

## Microservices

- **userService** - User management and authentication

## Tech Stack

- Java 21
- Spring Boot 4.1.1
- PostgreSQL
- Flyway (Schema Migrations)
- Lombok
- MapStruct
- JPA/Hibernate
- Spring Security Crypto (BCrypt password hashing)

## Getting Started

### Prerequisites
- Java 21+
- PostgreSQL
- Maven

### Setup

1. Clone the repository
2. Create `.env` file in `userService` directory with database credentials
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Reference

### User Service

| Method | Endpoint                 | Description            |
|--------|--------------------------|------------------------|
| POST   | `/api/v1/users/register` | Register a new user    |

#### Register a user

**Request**

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "emailId": "john.doe@example.com",
  "password": "secret123"
}
```

| Field       | Rules                              |
|-------------|------------------------------------|
| `firstName` | Required, max 255 characters       |
| `lastName`  | Optional, max 255 characters       |
| `emailId`   | Required, valid email, max 255 characters, unique |
| `password`  | Required, 8-72 characters          |

**Response** — `201 Created`

```json
{
  "success": true,
  "error": null,
  "message": "User registered successfully",
  "data": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "emailId": "john.doe@example.com",
    "role": "USER",
    "createdAt": "2026-10-10T12:00:00"
  }
}
```

**Errors**

| Status | Cause                                        |
|--------|----------------------------------------------|
| 400    | Validation failure (details in `error`)      |
| 409    | A user with this email already exists        |

## Architecture

- Entity-based schema design with BaseEntity for auditing
- Soft deletes for data preservation
- Database migrations with Flyway
- JPA Auditing for automatic timestamp management
