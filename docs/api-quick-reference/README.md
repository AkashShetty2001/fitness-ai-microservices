# API Quick Reference

## userService

**Endpoints implemented: none.**

`userService` includes the Spring Web MVC starter, but the codebase has no `@RestController` / `@RequestMapping` classes yet, so no HTTP endpoints are exposed. The service starts, connects to PostgreSQL, applies Flyway migrations and validates the schema against the entities.

When an endpoint is added, document it in this folder (one file per endpoint, e.g. `post-users.md`) using the template below.

### Endpoint template

```
# <METHOD> <path>

Short description.

## Request
| Part | Name | Type | Required | Notes |
| --- | --- | --- | --- | --- |
| Header |  |  |  |  |
| Path |  |  |  |  |
| Query |  |  |  |  |
| Body |  |  |  |  |

## Response
| Status | Meaning | Body |
| --- | --- | --- |

## Example
(request / response JSON)
```

### Data shape available for future endpoints

The `User` entity (table `users`) has these fields:

| Field | JSON-style name | Type | Constraints |
| --- | --- | --- | --- |
| id | `id` | long | generated, primary key |
| firstName | `firstName` | string | optional |
| lastName | `lastName` | string | optional |
| emailId | `emailId` | string | unique, not null in the database |
| password | `password` | string | not null |
| role | `role` | enum `USER` \| `ADMIN` | defaults to `USER` |
| createdAt | `createdAt` | timestamp | set automatically, not updatable |
| updatedAt | `updatedAt` | timestamp | set automatically on update |
| deletedAt | `deletedAt` | timestamp | set on soft delete |
