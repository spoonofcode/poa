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
                        api(project(":core:presentation"))

                        api(libs.findLibrary("koin-core").get())
                        api(libs.findLibrary("koin-compose").get())
                        api(libs.findLibrary("koin-compose-viewmodel").get())
                    }
                }
            }
        }
    }
}
