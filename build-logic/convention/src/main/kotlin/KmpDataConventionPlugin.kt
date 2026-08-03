import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpDataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                applyPlugin("convention-kmp-library")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    getByName("commonMain").dependencies {
                        implementation(libs.findLibrary("koin-core").get())
                        implementation(libs.findLibrary("ksafe").get())

                        implementation(project(":core:data"))
                        implementation(project(":core:network"))
                    }
                }
            }
        }
    }
}
