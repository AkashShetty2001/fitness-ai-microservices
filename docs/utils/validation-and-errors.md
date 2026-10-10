# Bean Validation and Error Handling

## Validation
`spring-boot-starter-validation` provides Jakarta Bean Validation (Hibernate Validator). Annotations on `RegisterUserRequestDto` declare the rules:

| Annotation | Meaning |
| --- | --- |
| `@NotBlank` | Not null, and not only whitespace |
| `@Size(min, max)` | Length bounds on strings |
| `@Email` | Must look like an email address |

`@Valid @RequestBody` on the controller parameter makes Spring run the checks after JSON binding and before the controller body runs. Failures throw `MethodArgumentNotValidException`.

## Global exception handling
`@RestControllerAdvice` on `GlobalExceptionHandler` applies handlers to every controller:

| Exception | Status |
| --- | --- |
| `MethodArgumentNotValidException` | 400, field errors sorted and joined with `; ` |
| `UserAlreadyExistsException` | 409 |
| `DataIntegrityViolationException` | 409 |

Each handler returns a `UserApiResponse` with `success=false`, so clients always receive the same envelope.

## Response envelope
`UserApiResponse<T>` is a generic wrapper with static factories `success(message, data)` and `error(message)`.
