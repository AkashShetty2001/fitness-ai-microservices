# Utilities and Libraries

What the code uses today and how each piece works. Versions come from `userService/pom.xml`.

| Topic | File |
| --- | --- |
| BCrypt password hashing | [bcrypt-password-hashing.md](bcrypt-password-hashing.md) |
| Bean Validation and error handling | [validation-and-errors.md](validation-and-errors.md) |
| MapStruct mapping | [mapstruct.md](mapstruct.md) |
| JPA auditing and soft delete | [persistence.md](persistence.md) |
| Flyway migrations | [flyway.md](flyway.md) |
| `.env` and application configuration | [configuration.md](configuration.md) |
| Lombok | [lombok.md](lombok.md) |

## Dependency overview

| Dependency | Role in the project |
| --- | --- |
| Java 21 | Language/runtime |
| Spring Boot 4.1.1 (parent) | Dependency management and auto-configuration |
| `spring-boot-starter-webmvc` | HTTP controllers (`UserController`) |
| `spring-boot-starter-data-jpa` | JPA/Hibernate and Spring Data repositories |
| `spring-boot-starter-validation` | Bean Validation on request DTOs |
| `spring-security-crypto` | `BCryptPasswordEncoder` (no Spring Security filter chain) |
| `mapstruct` 1.6.3 + `mapstruct-processor` | Compile-time DTO/entity mapping |
| `lombok` + `lombok-mapstruct-binding` 0.2.0 | Boilerplate generation, compatible with MapStruct |
| `postgresql` (runtime) | JDBC driver |
| `spring-boot-starter-flyway`, `flyway-core`, `flyway-database-postgresql` | Versioned schema migrations |
| `java-dotenv` 5.2.2 | Loads `.env` into system properties |
| `spring-boot-starter-validation-test`, `spring-boot-starter-webmvc-test` (test) | Test support |

## Not in the code yet

JWT and Kafka. Add a page here when they are introduced.
