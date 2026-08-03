import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpPresentationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                applyPlugin("convention-cmp-library")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    getByName("commonMain").dependencies {
                        implementation(project(":core:presentation"))

                        implementation(libs.findLibrary("koin-core").get())
                        implementation(libs.findLibrary("koin-compose").get())
                        implementation(libs.findLibrary("koin-compose-viewmodel").get())
                    }
                }
            }
        }
    }
}
