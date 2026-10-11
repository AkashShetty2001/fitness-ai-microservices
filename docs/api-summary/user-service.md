# userService

## Startup

1. `UserServiceApplication.main` loads an optional `.env` file with `java-dotenv` and copies its entries into JVM system properties.
2. Spring Boot resolves `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` from `application.yaml`.
3. Flyway applies `db/migration` scripts (currently `V1__Create_users_table.sql`).
4. Hibernate validates entities against the schema (`ddl-auto: validate`).
5. JPA auditing is enabled, so `createdAt`/`updatedAt` are filled automatically.

## Layers

| Layer | Class | Responsibility |
| --- | --- | --- |
| Controller | `UserController` | Maps `POST /api/v1/users/register` (201) and `GET /api/v1/users/{userId}` (200), triggers validation with `@Valid` on register, wraps results in `UserApiResponse` |
| DTO | `RegisterUserRequestDto`, `RegisterUserResponseDto`, `UserProfileResponseDto` | Request shape with validation rules; response shapes without password |
| Service | `UserService` | Registration logic in one transaction; profile lookup |
| Mapper | `UserMapper` (MapStruct) | DTO ↔ entity; `role` and `password` are ignored when mapping the request |
| Repository | `UserRepository` | `JpaRepository<User, Long>` plus `existsByEmailId` |
| Config | `PasswordConfig` | Exposes a `BCryptPasswordEncoder` as the `PasswordEncoder` bean |
| Errors | `GlobalExceptionHandler`, `UserAlreadyExistsException`, `UserNotFoundException` | Converts failures to `UserApiResponse` errors |
| Response | `UserApiResponse<T>` | `success`, `error`, `message`, `data` envelope with `success(...)` / `error(...)` factories |

## Register user: step by step

`POST /api/v1/users/register`

1. **Deserialize and validate.** The JSON body is bound to `RegisterUserRequestDto`; the Bean Validation annotations are checked. On failure `MethodArgumentNotValidException` is thrown and the handler returns `400` with all field errors.
2. **Normalize the email.** `UserService.registerUser` trims it and lower-cases it.
3. **Duplicate check.** `existsByEmailId(normalizedEmail)`; if true, `UserAlreadyExistsException` → `409`.
4. **Build the entity.** `UserMapper.toEntity` copies `firstName`, `lastName`, `emailId`; `role` is not mapped, so it takes the entity default `USER`. The email is set to the normalized value.
5. **Hash the password.** `passwordEncoder.encode(...)` (BCrypt) and the result is stored in `password`.
6. **Save.** `userRepository.save(user)` inserts the row; `createdAt` is set by auditing.
7. **Respond.** `UserMapper.toResponseDto` produces the response DTO; the controller returns `201` with message `User registered successfully`.

## Get user profile: step by step

`GET /api/v1/users/{userId}`

1. **Bind the path variable.** `userId` is converted to `Long`.
2. **Look up the user.** `userRepository.findById(userId)`; soft-deleted rows are excluded by `@SQLRestriction`. If nothing is found, `UserNotFoundException` → `404`.
3. **Map to the response.** `UserMapper.toUserProfileResponseDto` copies `id`, `firstName`, `lastName`, `emailId`, `role`. The password is not part of the DTO.
4. **Respond.** The controller returns `200` with message `User profile retrieved successfully`.

The lookup is a single query on the `users` table; `User` has no relationships, so there is no extra loading.

## Error handling

| Exception | Status | `error` text |
| --- | --- | --- |
| `MethodArgumentNotValidException` | 400 | `field: message` entries, sorted, joined by `; ` |
| `UserAlreadyExistsException` | 409 | `User with email <email> already exists` |
| `DataIntegrityViolationException` | 409 | `User with this email already exists` |
| `UserNotFoundException` | 404 | `User with ID <id> not found` |

The last case covers two simultaneous registrations that both pass the `existsBy` check: the database unique constraint rejects the second insert. It also covers an email that belongs to a soft-deleted user, because soft-deleted rows are hidden from `existsByEmailId` but still occupy the unique `email_id` value.

Other failures (for example malformed JSON or a non-numeric `userId`) fall through to Spring Boot's default error handling.

## Data behaviour

| Behaviour | Where | Effect |
| --- | --- | --- |
| Soft delete | `@SQLDelete` on `User` | Deleting runs `UPDATE users SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?` |
| Hidden deleted rows | `@SQLRestriction("deleted_at IS NULL")` | JPA queries do not return soft-deleted users |
| Unique email | `email_id` unique constraint | Duplicate insert fails at the database |
| Default role | `@Builder.Default role = USER` | Users default to `USER` |
| Password in logs | `@ToString.Exclude` on `password` | Hash does not appear in `User.toString()` |
| Timestamps | `BaseEntity` + auditing | `createdAt` on insert, `updatedAt` on update |
