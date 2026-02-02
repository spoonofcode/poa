import com.spoonofcode.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            // TODO #109 Fix after migration to feature modules
//            with(pluginManager) {
//                apply("com.spoonofcode.convention.cmp.library")
//            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    getByName("commonMain").dependencies {
                        // Projekty Core
                        implementation(project(":core:presentation"))
                        implementation(project(":core:data"))
                        implementation(project(":feature:appnavigation"))

                        // Kotlin & Resources
                        implementation(libs.findLibrary("kotlin-stdlib").get())
                        implementation(libs.findLibrary("components-resources").get())

                        // UI & Animations
                        implementation(libs.findLibrary("compose-icons-fontawesome").get())
                        implementation(libs.findLibrary("compottie").get())
                        implementation(libs.findLibrary("compottie-dot").get())
                        implementation(libs.findLibrary("compottie-network").get())
                        implementation(libs.findLibrary("landscapist-coil").get())

                        // Connectivity
                        implementation(libs.findLibrary("connectivity-core").get())
                        implementation(libs.findLibrary("connectivity-device").get())

                        // Compose Multiplatform
                        implementation(libs.findLibrary("foundation").get())
                        implementation(libs.findLibrary("runtime").get())
                        implementation(libs.findLibrary("material3").get())
                        implementation(libs.findLibrary("ui").get())
                        implementation(libs.findLibrary("ui-tooling-preview").get())
                        implementation(libs.findLibrary("material-icons-core").get())
                        implementation(libs.findLibrary("material-icons-extended").get())

                        // Lifecycle & ViewModel
                        implementation(libs.findLibrary("lifecycle-viewmodel-compose").get())

                        // Navigation (Voyager)
                        implementation(libs.findLibrary("voyager-navigator").get())
                        implementation(libs.findLibrary("voyager-tab-navigator").get())
                        implementation(libs.findLibrary("voyager-transitions").get())

                        // Dependency Injection (Koin)
                        implementation(libs.findLibrary("koin-core").get())
                        implementation(libs.findLibrary("koin-compose").get())
                        implementation(libs.findLibrary("koin-compose-viewmodel").get())
                    }

//                    getByName("androidMain").dependencies {
//                        implementation(libs.findLibrary("koin-android").get())
//                        implementation(libs.findLibrary("koin-androidx-compose").get())
//                    }
                }
            }
        }
    }
}
