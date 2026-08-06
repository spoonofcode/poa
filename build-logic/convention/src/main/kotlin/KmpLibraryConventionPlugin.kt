import com.android.build.api.dsl.LibraryExtension
import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.configureAndroidTarget
import com.spoonofcode.convention.configureKotlinAndroid
import com.spoonofcode.convention.configureKotlinMultiplatform
import com.spoonofcode.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                applyPlugin("android-library")
                applyPlugin("kotlin-multiplatform")
            }

            configureKotlinMultiplatform()
            configureAndroidTarget()

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.getByName("commonTest").dependencies {
                    implementation(libs.findLibrary("kotlin-test").get())
                }
            }
        }
    }
}
