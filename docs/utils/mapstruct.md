# MapStruct

MapStruct generates mapping classes at compile time from an annotated interface. There is no reflection at runtime; the generated code is plain getter/setter calls.

## In this project
`UserMapper` is annotated `@Mapper(componentModel = "spring")`, so the generated implementation is a Spring bean that can be injected.

| Method | Purpose |
| --- | --- |
| `User toEntity(RegisterUserRequestDto)` | Copies same-named fields; `role` and `password` are explicitly ignored (`@Mapping(target = ..., ignore = true)`) so the service controls them |
| `RegisterUserResponseDto toResponseDto(User)` | Copies `id`, `firstName`, `lastName`, `emailId`, `role`, `createdAt` |
| `UserProfileResponseDto toUserProfileResponseDto(User)` | Copies `id`, `firstName`, `lastName`, `emailId`, `role`; `password` has no target field, so it is never mapped |

## Build setup
`maven-compiler-plugin` lists these annotation processors, in this order: `lombok`, `lombok-mapstruct-binding` (0.2.0), `mapstruct-processor`. The binding makes MapStruct see Lombok-generated getters and setters. Generated classes appear under `target/generated-sources/annotations`.
