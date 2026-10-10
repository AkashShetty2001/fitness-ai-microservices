# Lombok

Lombok is an annotation processor that generates code during compilation. It is configured in `pom.xml` as an optional dependency and under `annotationProcessorPaths` of `maven-compiler-plugin`.

| Annotation | Used on | Generates |
| --- | --- | --- |
| `@Data` | `BaseEntity`, `User` | getters, setters, `toString`, `equals`, `hashCode` |
| `@NoArgsConstructor` | `BaseEntity`, `User` | no-argument constructor (required by JPA) |
| `@AllArgsConstructor` | `User` | constructor with every field |
| `@Builder` | `User` | fluent builder: `User.builder().firstName("A").build()` |
| `@EqualsAndHashCode(callSuper = true)` | `User` | equality that also compares `BaseEntity` fields |
