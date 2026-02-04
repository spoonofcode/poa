import com.android.build.api.dsl.LibraryExtension
import com.spoonofcode.convention.configureKotlinAndroid
//import com.spoonofcode.convention.configureKotlinAndroid
//import com.spoonofcode.convention.configureKotlinAndroid
import com.spoonofcode.convention.configureKotlinMultiplatform
import com.spoonofcode.convention.libs
import com.spoonofcode.convention.pathToResourcePrefix
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class KmpLibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("android-library").get().get().pluginId)
                apply(libs.findPlugin("kotlin-multiplatform").get().get().pluginId)
                apply(libs.findPlugin("kotlin-serialization").get().get().pluginId)
            }

            configureKotlinMultiplatform()

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                resourcePrefix = this@with.pathToResourcePrefix()

                // Required to make debug build of app run in iOS simulator
                experimentalProperties["android.experimental.kmp.enableAndroidResources"] = "true"
            }

            dependencies {
                "commonMainImplementation"(libs.findLibrary("kotlinx-serialization-json").get())
                "commonTestImplementation"(libs.findLibrary("kotlin-test").get())
            }
        }
    }
}