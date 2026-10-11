# GET /api/v1/users/{userId}

Returns the profile of a single user. The password (plain or hashed) is never included.

## Request

| Path variable | Type | Description |
| --- | --- | --- |
| `userId` | long | ID of the user |

## Responses

### 200 OK

```json
{
  "success": true,
  "error": null,
  "message": "User profile retrieved successfully",
  "data": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "emailId": "john.doe@example.com",
    "role": "USER"
  }
}
```

### 404 Not Found: user does not exist

Also returned for soft-deleted users, which are hidden from queries.

```json
{
  "success": false,
  "error": "User with ID 99 not found",
  "message": null,
  "data": null
}
```

## Status codes

| Status | Cause |
| --- | --- |
| 200 | User found |
| 404 | No active user with this ID |
