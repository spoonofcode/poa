# AGENTS.md - Root Instructions

> **Note:** This file provides general guidance for AI agents working on this project. For
> specialized agents (testing, documentation, security), see `.github/agents/` directory.

## Your Role

You are an expert developer assistant for the "Taggy Go" Compose Multiplatform application. You
understand Kotlin, Jetpack Compose, Clean Architecture, and Compose Multiplatform best practices.

### Quick Reference

- **Tech Stack:** Kotlin 2.x + Jetpack Compose + Clean Architecture + MVVM/MVI
- **Build Tool:** Gradle with Kotlin DSL
- **Main Directories:** `poa/` (app), `core/` (core business logic), `feature/` (features), `docs/`
  (documentation)
- **Key Commands:** `./gradlew ktlintFormat detekt`, `./gradlew testDebugUnitTest`
- **Primary Branch:** `develop` (all PRs target this branch)

## Language Conventions

| Context         | Language | Example                            |
|-----------------|----------|------------------------------------|
| Github Tickets  | English  | Titel, description, comments       |
| Code            | English  | Variables, functions, comments     |
| Commit Messages | English  | `POA-123 - Implement login screen` |
| Documentation   | English  | ADRs, README, code docs            |
| Pull Requests   | English  | Title, description, Reviews        |

<!-- 
TODO THIS LATER:
## Commands You Can Use

```bash
# Format & lint
./gradlew ktlintFormat detekt --continue

# Unit tests
./gradlew testDebugUnitTest testDevelopmentDebugUnitTest --parallel --quiet

# Clean build
./gradlew clean --continue

# Assemble
./gradlew :app:assembleDebug :app:assembleDevelopmentDebug --parallel

# Lint check
./gradlew :poa:composeApp:lintDevelopmentRelease --continue
```
-->

## Github Ticket Creation

### Required Fields

Take does values as defaults when creating (when not specified explicitly) new tickets:

| Field         | Value   | Description |
|---------------|---------|-------------|
| Project       | `POA`   | Projekt-Key |
| Issue Type ID | `10202` | Tech Story  |
| Component ID  | `11204` | Android     |

### Ticket Structure (English)

```
h2. Problem
[Brief description of the problem]

h2. Solution
[Describe the proposed solution]

h2. Affected files
|| File || Change ||
| path/to/file.kt | Description |

h2. Acceptance criteria
* [ ] Criterion 1
* [ ] Criterion 2

h2. Effort
[X days]
```

### Title Guidelines

- Rather focus on **benefits**, not technical solution
- Keep concise (max 120 characters)

**Good:** `Faster analysis of backend issues via in-app debugging`
**Bad:** `Integration of the Chucker library for OkHttp interceptors`
<!-- 
TODO THIS LATER:
### MCP Integration

For AI-assisted JIRA ticket creation and management, see [MCP Setup Guide](docs/MCP_SETUP.md).
-->

## Project Structure

```
poa/ # Main app module
 composeApp/ # Application entry point
 feature/ # Feature modules (Clean Architecture)
core/ # Core business logic
build-logic/ # Build configuration, plugins
docs/ # Documentation, ADRs
```

## Build Variants

- **Flavors:** `development`, `staging`, `production`
- **Build types:** `debug`, `release`
- **Examples:** `developmentDebug`, `developmentRelease`, `productionRelease`

## Tech Stack

| Category     | Technology                    |
|--------------|-------------------------------|
| Language     | Kotlin 2.x                    |
| UI           | Jetpack Compose               |
| Architecture | Clean Architecture + MVVM/MVI |
| DI           | Koin                          |
| Networking   | Ktor                          |
| Async        | Coroutines + Flow             |
| Navigation   | Navigation Compose 3          |
| Testing      | JUnit 5 + Mokkery + Turbine   |
| CI/CD        | Jenkins                       |

## Code Style

**Naming conventions:**

- Classes: PascalCase (`UserRepository`, `LoginViewModel`)
- Functions: camelCase (`fetchUserData`, `validateInput`)
- Constants: UPPER_SNAKE_CASE (`MAX_RETRY_COUNT`, `API_TIMEOUT`)
- Composables: PascalCase with descriptive names (`LoginScreen`, `UserProfileCard`)

<!-- 
TODO THIS LATER:
**Good code example:**
```kotlin
// Good - descriptive names, proper error handling, coroutines
class UserRepository(
 private val apiService: ApiService
) {
 suspend fun fetchUserById(userId: String): Result {
 if (userId.isBlank()) {
 return Result.failure(IllegalArgumentException("User ID required"))
 }
 
 return try {
 val response = apiService.getUser(userId)
 Result.success(response)
 } catch (e: Exception) {
 Result.failure(e)
 }
 }
}
```

**Bad code example:**
```kotlin
// Bad - vague names, no error handling, blocking call
class Repo {
 fun get(x: String): User {
 return api.getUser(x).execute().body()!!
 }
}
```

**Compose style:**
```kotlin
// Good - clear state management, proper modifiers
@Composable
fun LoginScreen(
 viewModel: LoginViewModel = koinViewModel(),
 onNavigateToHome: () -> Unit
) {
 val uiState by viewModel.uiState.collectAsState()
 
 Column(
 modifier = Modifier
 .fillMaxSize()
 .padding(16.dp),
 verticalArrangement = Arrangement.Center
 ) {
 // UI implementation
 }
}
```
-->

<!-- 
TODO THIS LATER:
**Guidelines:**
- Run `ktlintFormat` and `detekt` before commits
- Follow Detekt rules (see `detekt.yml`)
- Write ADRs for significant decisions → `docs/adr/`
- Comment code that is not self-explanatory
-->

## Git Workflow

- **Main branch:** `main`
- **Develop branch:** `develop`
- Branch naming: `feature/`, `hotfix/`, `release/`
- Commit messages: `{ticket-number} - {message}`, e.g. `POA-#123 - Implement login screen`

### Versioning Convention

| Environment  | Pattern                       | Example                             |
|--------------|-------------------------------|-------------------------------------|
| Development  | `X.Y.Z-rc#-{branch}-SNAPSHOT` | `5.20.0-rc1-develop-SNAPSHOT`       |
| Feature      | `X.Y.Z-rc#-{branch}-SNAPSHOT` | `5.20.0-rc1-feature-login-SNAPSHOT` |
| Staging (RC) | `X.Y.Z-rc#`                   | `5.20.0-rc1`                        |
| Production   | `X.Y.Z`                       | `5.20.0`                            |
| Hotfix       | `X.Y.Z`                       | `5.20.1`                            |

<!-- 
TODO THIS LATER:
## Boundaries

** Always do:**
- Run `ktlintFormat` and `detekt` before committing code
- Write tests for new features (`testDebugUnitTest`)
- Follow naming conventions (PascalCase for classes, camelCase for functions)
- Use proper error handling and null safety
- Document architectural decisions in `docs/adr/`

** Ask first:**
- Before modifying database schemas or migrations
- Before adding new dependencies to `build.gradle.kts`
- Before changing CI/CD configuration (Jenkinsfile)
- Before disabling lint/detekt warnings

** Never do:**
- Commit secrets, API keys, or credentials
- Bypass security checks without documented justification
- Modify files in `build/`, `node_modules/`, or `vendor/`
- Edit generated files or third-party SDK code
- Remove failing tests without fixing the underlying issue
- Disable accessibility features
- Commit directly to `develop` branch (use PRs)
-->