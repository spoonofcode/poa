import com.spoonofcode.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.spoonofcode.convention.cmp.library")
            }

            // KONFIGURACJA KMP - musi być bezpośrednio w Project, nie w dependencies {}
            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    getByName("commonMain").dependencies {
                        // Zależności projektowe
                        implementation(project(":core:presentation"))
                        implementation(project(":core:designsystem"))

                        // Biblioteki z libs.versions.toml (używając poprawnych aliasów)
                        implementation(libs.findLibrary("koin-compose").get())
                        implementation(libs.findLibrary("koin-compose").get())
                        implementation(libs.findLibrary("koin-compose-viewmodel").get())

                        implementation(libs.findLibrary("runtime").get()) // To jest jetbrains-compose-runtime
                        implementation(libs.findLibrary("lifecycle-viewmodel-compose").get())

                        // Nawigacja (Voyager)
                        implementation(libs.findLibrary("voyager-navigator").get())
                        implementation(libs.findLibrary("voyager-tab-navigator").get())
                        implementation(libs.findLibrary("voyager-transitions").get())
                    }

                    getByName("androidMain").dependencies {
                        implementation(libs.findLibrary("koin-android").get())
                        implementation(libs.findLibrary("koin-androidx-compose").get())
                    }
                }
            }
        }
    }
}
