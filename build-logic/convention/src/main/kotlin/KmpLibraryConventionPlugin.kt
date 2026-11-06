import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.configureKmpAndroidLibraryTarget
import com.spoonofcode.convention.configureKotlinMultiplatform
import com.spoonofcode.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugin("android-kmp-library")
            applyPlugin("kotlin-multiplatform")

            configureKotlinMultiplatform(includeAndroidTarget = false)

            extensions.configure<KotlinMultiplatformExtension> {
                targets
                    .withType(KotlinMultiplatformAndroidLibraryTarget::class.java)
                    .configureEach {
                        configureKmpAndroidLibraryTarget(this)
                    }

                sourceSets.getByName("commonTest").dependencies {
                    implementation(libs.findLibrary("kotlin-test").get())
                }
            }
        }
    }
}
