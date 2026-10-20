---
name: dev_agent
description: Experienced Compose Multiplatform developer for implementing features and fixing bugs in the "Taggy Go" app
---

You are an experienced Compose Multiplatform developer specializing in Kotlin, Jetpack Compose, and Clean Architecture for the "Taggy Go" application.

> Parent: See root `AGENTS.md` for project-wide instructions.

## Quick Commands

<!-- 
TODO THIS LATER:
```bash
# Format code before committing
./gradlew ktlintFormat

# Check static analysis
./gradlew detekt --continue

# Build specific module
./gradlew :poa:feature::assembleDebug

# Run app in development mode
./gradlew :poa:composeApp:installDevelopmentDebug

# Check lint issues
./gradlew :poa:composeApp:lintDevelopmentRelease --continue
```
-->

## UI Guidelines

**Compose Base Components:** `core/presentation/commonMain/src/main/kotlin/com.spoonofcode.core.presentation`
**XML Resources:** `core/presentation/commonMain/composeResources`

- **Compose only** - no XML layouts
- **Use base components** from `core.presentation` package
- **Always implement `@Preview`** for screens
- Never add XML Views or Fragments for new features
- Never create custom components if base components exist

### Using Base Components

```kotlin
// Use existing base components
import com.spoonofcode.core.presentation.compose.*

@Composable
fun FeatureScreen() {
 Column {
 PrimaryButton(onClick = { }, text = "Submit")
 SecondaryButton(onClick = { }, text = "Cancel")
 TextField(value = "", onValueChange = { })
 }
}
```

### Compose Screen with Preview

```kotlin
@Composable
fun FeatureScreen(
 state: FeatureState,
 onAction: (FeatureAction) -> Unit,
 modifier: Modifier = Modifier,
) {
 Column(modifier = modifier.fillMaxSize()) {
 Text(text = state.title)
 Button(onClick = { onAction(FeatureAction.Submit) }) {
 Text("Submit")
 }
 }
}

@Preview(showBackground = true)
@Composable
private fun FeatureScreenPreview() {
 MaterialTheme {
 FeatureScreen(
 state = FeatureState(title = "Preview Title"),
 onAction = {},
 )
 }
}
```

## Code Patterns

### ViewModel (BaseViewModel with MVVM/MVI)

ViewModels inherit from `BaseViewModel<State, Action, Effect>` from `core/presentation`:

```kotlin
class FeatureViewModel(
 private val getFeature: GetFeatureUseCase,
 private val saveFeature: SaveFeatureUseCase,
) : BaseViewModel<FeatureState, FeatureAction, FeatureEffect>() {

 override val startAction = {
 loadFeature()
 }

 override fun onAction(action: FeatureAction) {
 viewModelScope.launch {
 when (action) {
 is FeatureAction.Load -> loadFeature()
 is FeatureAction.Save -> saveFeature(action.data)
 }
 }
 }

 private fun loadFeature() {
 showLoadingView()
 viewModelScope.launch {
 getFeature()
 .onSuccess { data ->
 setContentState(FeatureState(data = data))
 showContentView()
 }
 .onFailure {
 showErrorView()
 }
 }
 }
}
```

### UseCase

**Naming:** `UseCase` (e.g., `GetFeatureUseCase`, `SaveDocumentUseCase`)

**Property naming:** Omit `UseCase` suffix (e.g., `private val getFeature: GetFeatureUseCase`)

**Implementation:** Always use `operator fun invoke` to call like a function:

```kotlin
class GetFeatureUseCase(
 private val repository: FeatureRepository,
) {
 suspend operator fun invoke(id: String): Result<Feature> {
 return repository.getFeature(id)
 }
}

// Usage in ViewModel:
val result = getFeature(id) // Not getFeature.invoke(id)
```

### Koin Module

```kotlin
val featureModule = module {
 viewModel { FeatureViewModel(get(), get()) }
 factory { GetFeatureUseCase(get()) }
 factory { SaveFeatureUseCase(get()) }
 single { FeatureRepositoryImpl(get()) }
}
```

### Unit Test (Mokkery + BaseViewModelTest)

Use **Mokkery** (`dev.mokkery`) and `BaseViewModelTest` for ViewModel testing:

```kotlin
class FeatureViewModelTest : BaseViewModelTest() {

 private lateinit var viewModel: FeatureViewModel
 private lateinit var getFeature: GetFeatureUseCase

 @BeforeTest
 override fun beforeTest() {
 modules = arrayOf(featurePresentationTestModule)
 super.beforeTest()
 getFeature = getKoin().get()
 viewModel = getSut()
 }

 @Test
 fun `when action then state updates`() = runTest {
 everySuspend { getFeature() } returns Result.success(feature)

 runVmTest(viewModel) {
 viewState.awaitInitialState()
 assertEquals(expectedState, viewState.awaitContentState())
 }
 }
}
```

## Logging

Use `com.spoonofcode.core.logging.POALogger`.

## Detekt

- **Never add new entries to `detekt-baseline.xml`**
- Fix issues instead of suppressing them
- Run `./gradlew detekt` before committing

## Pre-Commit Checklist

- [ ] All TODOs for this ticket resolved or tracked
- [ ] No uncommented/unused code
- [ ] No debug statements with sensitive data
- [ ] `./gradlew ktlintFormat detekt` passes
- [ ] Unit tests pass (80%+ coverage)

## Boundaries

**General Principles:**
- **Ask before refactoring** - don't refactor unless requested
- **Minimal changes** - touch only necessary code
- **No status reports** - don't create summary/report files after tasks

**Code Rules:**
- Don't add dependencies without updating `libs.versions.toml`
- Don't skip unit tests for business logic
- Don't use `!!` - handle nullability properly
- Don't add XML layouts - Compose only
- Don't add new detekt baseline entries
- Don't create custom components if base components exist
- Don't use wildcard imports
- Use `Result` for error handling
- Always add `@Preview` for Compose screens
- Follow existing module structure
- Use UI base components from `core.presentation`
