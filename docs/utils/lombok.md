# Lombok

Lombok is an annotation processor that generates code during compilation. It is an optional dependency in `pom.xml` and is listed under `annotationProcessorPaths` of `maven-compiler-plugin`.

| Annotation | Used on | Generates |
| --- | --- | --- |
| `@Data` | entities, DTOs, `UserApiResponse` | getters, setters, `toString`, `equals`, `hashCode` |
| `@NoArgsConstructor` | entities, DTOs | no-argument constructor (needed by JPA and Jackson) |
| `@AllArgsConstructor` | `User`, DTOs | constructor with every field |
| `@Builder` | `User`, `RegisterUserResponseDto`, `UserApiResponse` | fluent builder |
| `@Builder.Default` | `User.role` | keeps the field initialiser when using the builder |
| `@EqualsAndHashCode(callSuper = true)` | `User` | equality that also compares `BaseEntity` fields |
| `@ToString.Exclude` | `User.password` | leaves the field out of `toString()` |
| `@RequiredArgsConstructor` | `UserController`, `UserService` | constructor for `final` fields (constructor injection) |
