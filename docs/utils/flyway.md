# Flyway

Flyway applies versioned SQL scripts to the database in order and records what has run.

## How it works
1. On startup Flyway reads scripts from `classpath:db/migration` (`userService/src/main/resources/db/migration`).
2. File names follow `V<version>__<description>.sql` (two underscores).
3. It creates a `flyway_schema_history` table and stores each applied script with a checksum.
4. Only scripts newer than the recorded version run. Editing an already-applied script changes its checksum and makes startup fail, so add a new version instead.

## Settings (`application.yaml`)
| Setting | Value | Meaning |
| --- | --- | --- |
| `spring.flyway.enabled` | `true` | Run migrations at startup |
| `spring.flyway.locations` | `classpath:db/migration` | Script folder |
| `spring.flyway.baselineOnMigrate` | `true` | If the database already has tables but no history, treat it as a baseline instead of failing |

## Current migrations
- `V1__Create_users_table.sql`: creates `users` (`BIGSERIAL` id, unique `email_id`, `created_at` defaulting to the current timestamp, plus `updated_at`, `deleted_at`).

## Adding a migration
Create the next file, e.g. `V2__Add_<change>.sql`, and update the entity to match; Hibernate's `validate` mode will flag mismatches.
