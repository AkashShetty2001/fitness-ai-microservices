# POST /api/v1/users/register

Registers a new user. The password is hashed with BCrypt before it is stored.

## Request

Headers: `Content-Type: application/json`

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "emailId": "john.doe@example.com",
  "password": "secret123"
}
```

| Field | Type | Required | Rules |
| --- | --- | --- | --- |
| `firstName` | string | yes | not blank, max 255 |
| `lastName` | string | no | max 255 |
| `emailId` | string | yes | not blank, valid email, max 255, must be unique |
| `password` | string | yes | not blank, 8–72 characters |

`role` cannot be supplied; new users always get `USER`.

## Responses

### 201 Created

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

The password (plain or hashed) is never returned.

### 400 Bad Request: validation failure

`error` lists every failing field as `field: message`, sorted alphabetically and joined with `; `.

```json
{
  "success": false,
  "error": "emailId: Email must be a valid email address; password: Password must be between 8 and 72 characters long",
  "message": null,
  "data": null
}
```

### 409 Conflict: email already registered

```json
{
  "success": false,
  "error": "User with email john.doe@example.com already exists",
  "message": null,
  "data": null
}
```

A concurrent duplicate that is caught by the database unique constraint returns `409` with `"User with this email already exists"`.

## Status codes

| Status | Cause |
| --- | --- |
| 201 | User created |
| 400 | Bean validation failed |
| 409 | Email already exists |
