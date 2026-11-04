import com.spoonofcode.convention.applyPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign

class BuildKonfigConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            // Auto-detect BuildKonfig flavor from running Gradle tasks (e.g. assembleProductionDebug -> production)
            // or from Xcode environment variable (e.g. CONFIGURATION=Debug-Production -> production)
            val taskNames = gradle.startParameter.taskNames
            val xcodeConfig = System.getenv("CONFIGURATION") ?: ""
            val activeFlavor = when {
                taskNames.any { it.contains("Production", ignoreCase = true) } || xcodeConfig.contains("Production", ignoreCase = true) -> "production"
                taskNames.any { it.contains("Staging", ignoreCase = true) } || xcodeConfig.contains("Staging", ignoreCase = true) -> "staging"
                taskNames.any { it.contains("Development", ignoreCase = true) } || xcodeConfig.contains("Development", ignoreCase = true) -> "development"
                else -> {
                    (findProperty("buildkonfig.flavor") as? String) ?: "development"
                }
            }
            extensions.extraProperties["buildkonfig.flavor"] = activeFlavor

            applyPlugin("buildkonfig")
        }
    }
}
