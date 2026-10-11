# API Summary

| Service | Purpose | Endpoints |
| --- | --- | --- |
| userService | User management and authentication | `POST /api/v1/users/register`, `GET /api/v1/users/{userId}` |

- [userService](user-service.md): request handling, layers, responses and data behaviour

## Not yet implemented

Login, token issuing, update/delete user endpoints, authorization on endpoints, Kafka and other services. The registration and profile endpoints are currently open (no authentication filter is configured), so any caller can read any profile by ID.
