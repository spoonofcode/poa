import com.spoonofcode.convention.applyHierarchyTemplate
import com.spoonofcode.convention.configureAndroidTarget
import com.spoonofcode.convention.configureIosTargets
import com.spoonofcode.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpApplicationConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.spoonofcode.convention.android.application.compose")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            configureAndroidTarget()
            configureIosTargets()

            extensions.configure<KotlinMultiplatformExtension> {
                applyHierarchyTemplate()
            }

            dependencies {
//                "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
            }
        }
    }
}