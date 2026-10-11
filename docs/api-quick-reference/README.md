# API Quick Reference

## userService

Base path: `/api/v1/users`

| Method | Path | Description | Success | Errors |
| --- | --- | --- | --- | --- |
| POST | `/api/v1/users/register` | Register a new user | 201 | 400, 409 |
| GET | `/api/v1/users/{userId}` | Get a user profile | 200 | 404 |

Details: [post-register.md](post-register.md), [get-user-profile.md](get-user-profile.md)

## Common response envelope

All userService endpoints return `UserApiResponse<T>`:

```json
{
  "success": true,
  "error": null,
  "message": "…",
  "data": { }
}
```

| Field | Success response | Error response |
| --- | --- | --- |
| `success` | `true` | `false` |
| `error` | `null` | error text |
| `message` | success text | `null` |
| `data` | payload | `null` |

## Adding a new endpoint

Create one file per endpoint named `<method>-<name>.md` and add it to the table above.
