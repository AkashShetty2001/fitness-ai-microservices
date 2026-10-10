# Configuration and `.env` Loading

## Properties used (`application.yaml`)
| Property | Source | Purpose |
| --- | --- | --- |
| `spring.datasource.url` | `${DB_URL}` | JDBC URL, e.g. `jdbc:postgresql://localhost:5432/<db>` |
| `spring.datasource.username` | `${DB_USERNAME}` | Database user |
| `spring.datasource.password` | `${DB_PASSWORD}` | Database password |
| `spring.jpa.show-sql` | `true` | Log generated SQL |
| `spring.jpa.hibernate.ddl-auto` | `validate` | Check schema, never alter it |
| `spring.jpa.database-platform` | `PostgreSQLDialect` | SQL dialect |

## How `.env` is loaded
`UserServiceApplication.main` calls `Dotenv.configure().ignoreIfMissing().load()` (java-dotenv) before starting Spring, then copies every entry into `System.setProperty`. Spring resolves `${DB_URL}` etc. from system properties, so values in `userService/.env` work without exporting shell variables. If the file is missing, loading is skipped and real environment variables are used.

Example `userService/.env`:
```
DB_URL=jdbc:postgresql://localhost:5432/fitness_user
DB_USERNAME=postgres
DB_PASSWORD=change-me
```
Keep `.env` out of version control.
