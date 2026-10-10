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

## User entity
Table `users`: `first_name`, `last_name`, `email_id` (unique), `password` (not null), `role` (`USER` or `ADMIN`, stored as text via `EnumType.STRING`) plus the `BaseEntity` columns.

Lombok `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` generate accessors and constructors. `@EqualsAndHashCode(callSuper = true)` includes the parent's fields in equality.

Note: with `@Builder`, the field initialiser `role = UserRole.USER` is ignored unless `@Builder.Default` is added; a user built through the builder without a role gets `null` in Java, while the database default applies only when the column is omitted from the insert.

## Schema validation
`spring.jpa.hibernate.ddl-auto: validate` makes Hibernate check entities against the real schema on startup and fail if they differ. Schema changes therefore go through Flyway, not Hibernate.
