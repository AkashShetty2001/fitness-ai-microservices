# Utilities and Libraries

What the code uses today and how each piece works. Versions come from `userService/pom.xml`.

| Topic | File |
| --- | --- |
| Overview of every dependency | this page |
| Soft delete and auditing | [persistence.md](persistence.md) |
| Flyway migrations | [flyway.md](flyway.md) |
| Environment configuration (`.env`) | [configuration.md](configuration.md) |
| Lombok | [lombok.md](lombok.md) |

## Dependency overview

| Dependency | Role in the project |
| --- | --- |
| Java 21 | Language/runtime |
| Spring Boot 4.1.1 (parent) | Dependency management, auto-configuration, `spring-boot-maven-plugin` |
| `spring-boot-starter-webmvc` | Servlet-based web layer for HTTP endpoints (no controllers yet) |
| `spring-boot-starter-data-jpa` | JPA/Hibernate and Spring Data repositories |
| `spring-boot-starter-validation` | Bean Validation (Jakarta) for request/entity constraints; not used by any class yet |
| `postgresql` (runtime) | JDBC driver for PostgreSQL |
| `spring-boot-starter-flyway`, `flyway-core`, `flyway-database-postgresql` | Versioned schema migrations |
| `java-dotenv` 5.2.2 | Loads `.env` into system properties |
| `lombok` (optional) | Generates boilerplate at compile time |
| `spring-boot-starter-validation-test`, `spring-boot-starter-webmvc-test` (test) | Test support |

## Not in the code yet

Password hashing (e.g. BCrypt), JWT and Kafka are not present. Add a page here when they are introduced.
