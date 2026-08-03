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
                        api(libs.findLibrary("koin-core").get())
                        api(libs.findLibrary("ksafe").get())

                        api(project(":core:data"))
                        api(project(":core:network"))
                    }
                }
            }
        }
    }
}
