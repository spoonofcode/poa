import com.spoonofcode.convention.applyPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign

class BuildKonfigConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugin("buildkonfig")

            // Auto-detect BuildKonfig flavor from running Gradle tasks (e.g. assembleProductionDebug -> production)
            val taskNames = gradle.startParameter.taskNames
            val activeFlavor = when {
                taskNames.any { it.contains("Production", ignoreCase = true) } -> "production"
                taskNames.any { it.contains("Staging", ignoreCase = true) } -> "staging"
                taskNames.any { it.contains("Development", ignoreCase = true) } -> "development"
                else -> {
                    (findProperty("buildkonfig.flavor") as? String) ?: "development"
                }
            }
            extensions.extraProperties["buildkonfig.flavor"] = activeFlavor
        }
    }
}
