# Persistence: Entities, Auditing and Soft Delete

## BaseEntity
`BaseEntity` is a `@MappedSuperclass`: its fields become columns of every subclass table, but it has no table itself.

| Field | Column | Notes |
| --- | --- | --- |
| `id` | `id` | `GenerationType.IDENTITY` maps to PostgreSQL `BIGSERIAL` |
| `createdAt` | `created_at` | `@CreatedDate`, not null, not updatable |
| `updatedAt` | `updated_at` | `@LastModifiedDate` |
| `deletedAt` | `deleted_at` | Set on soft delete |

## JPA auditing
- `@EnableJpaAuditing` on `UserServiceApplication` turns the feature on.
- `@EntityListeners(AuditingEntityListener.class)` on `BaseEntity` makes JPA call Spring's listener before persist/update.
- The listener fills `@CreatedDate` on insert and `@LastModifiedDate` on every update.

## Soft delete on `User`
- `@SQLDelete(sql = "UPDATE users SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")` replaces the SQL Hibernate runs for `delete(...)`. The row stays; `deleted_at` is set.
- `@SQLRestriction("deleted_at IS NULL")` appends that condition to every Hibernate query on `User`, so deleted users disappear from results.
- Native SQL and direct database access are not filtered.
- A soft-deleted user still holds its `email_id` in the unique constraint, so registering the same email again is rejected by the database (409).

## User entity
Table `users`: `first_name`, `last_name`, `email_id` (unique), `password` (not null), `role` (`USER` or `ADMIN`, stored as text via `EnumType.STRING`) plus the `BaseEntity` columns. `@Builder.Default` keeps the `USER` role default when the builder is used; `@ToString.Exclude` keeps the password out of `toString()`.

## Repository
`UserRepository extends JpaRepository<User, Long>` and adds `existsByEmailId(String)`, a derived query Spring Data builds from the method name.

## Schema validation
`spring.jpa.hibernate.ddl-auto: validate` makes Hibernate check entities against the real schema on startup and fail if they differ. Schema changes therefore go through Flyway.
