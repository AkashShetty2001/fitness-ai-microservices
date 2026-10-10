# BCrypt Password Hashing

## What it is
BCrypt is a one-way password hashing function designed to be slow. A hash cannot be reversed into the password; to check a login you hash the attempt the same way and compare.

## Where it is used
- `PasswordConfig` declares `new BCryptPasswordEncoder()` as the `PasswordEncoder` bean.
- `UserService.registerUser` calls `passwordEncoder.encode(rawPassword)` and stores the result in `users.password`.
- Library: `spring-security-crypto` (only the crypto module; the full Spring Security filter chain is not used).
- A matching check at login would be `passwordEncoder.matches(rawPassword, storedHash)`. Login is not implemented yet.

## How it works
1. **Random salt.** Each call to `encode` generates a new random 16-byte salt. Two users with the same password get different hashes, and precomputed (rainbow) tables are useless.
2. **Cost factor.** The work factor is the number of rounds `2^cost`. `BCryptPasswordEncoder()` defaults to cost 10 (1024 rounds). Raising it by 1 doubles the time, which slows brute-force attacks.
3. **Expensive key setup.** The password, salt and cost feed Blowfish's key schedule repeatedly (the "eksblowfish" setup), then the constant string `OrpheanBeholderScryDoubt` is encrypted 64 times. The output is the hash.
4. **Self-contained output.** Version, cost, salt and hash are stored together in one 60-character string.

## Stored format

```
$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy
\__/\/ \_____________________/\_____________________________/
 |   |          |                           |
 |   |          |                           31-char hash
 |   |          22-char salt (base64)
 |   cost (10 → 2^10 rounds)
 version ($2a$)
```

`matches` reads the version, cost and salt from the stored string, hashes the supplied password with them, and compares the result. No separate salt column is needed.

## Limits and project specifics
- **72-byte limit.** BCrypt only uses the first 72 bytes of input. `RegisterUserRequestDto` therefore caps `password` at 72 characters (and requires at least 8). Multi-byte characters can exceed 72 bytes within 72 characters, in which case the extra bytes are ignored.
- **Column size.** The hash is 60 characters; the `password` column is `VARCHAR(255)`.
- **Not logged or returned.** `@ToString.Exclude` keeps the hash out of `User.toString()`, and `RegisterUserResponseDto` has no password field.
- **Cost tuning.** To change the cost, use `new BCryptPasswordEncoder(strength)` (4–31). Existing hashes keep their stored cost and still verify.
