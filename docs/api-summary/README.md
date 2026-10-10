# API Summary

## Services

| Service | Purpose | HTTP endpoints |
| --- | --- | --- |
| userService | User management and authentication | None implemented yet |

## userService

### What happens today

1. `UserServiceApplication.main` loads an optional `.env` file (if present) with `java-dotenv` and copies each entry into JVM system properties.
2. Spring Boot starts and resolves `DB_URL`, `DB_USERNAME` and `DB_PASSWORD` from `application.yaml`.
3. Flyway runs the scripts in `classpath:db/migration` (currently `V1__Create_users_table.sql`).
4. Hibernate validates the entities against the schema (`ddl-auto: validate`); startup fails if they differ.
5. JPA auditing (`@EnableJpaAuditing`) is on, so `createdAt` and `updatedAt` are filled automatically.

### Incoming request handling

No controllers exist, so there is no request-to-response flow yet. Any HTTP request to the running service is answered by Spring Boot's default error handling (404 for unmapped paths).

### Data behaviour that future endpoints will inherit

| Behaviour | Where | Effect |
| --- | --- | --- |
| Soft delete | `@SQLDelete` on `User` | `delete` runs `UPDATE users SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?` instead of removing the row |
| Hidden deleted rows | `@SQLRestriction("deleted_at IS NULL")` | Queries through JPA never return soft-deleted users |
| Unique email | `email_id` unique constraint | Saving a duplicate email fails with a database constraint violation |
| Default role | `role = USER` | New users are `USER` unless set otherwise |
| Timestamps | `BaseEntity` + auditing | `createdAt` set once on insert; `updatedAt` refreshed on update |

### Not yet implemented

- REST endpoints (registration, login, profile, etc.)
- Password hashing: the `password` field is stored as supplied; no encoder is configured
- JWT, Kafka and other services
