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
- JPA/Hibernate

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

## Architecture

- Entity-based schema design with BaseEntity for auditing
- Soft deletes for data preservation
- Database migrations with Flyway
- JPA Auditing for automatic timestamp management
