import com.android.build.api.dsl.ApplicationExtension
import com.spoonofcode.convention.applyHierarchyTemplate
import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.configureAndroidCompose
import com.spoonofcode.convention.configureAndroidTarget
import com.spoonofcode.convention.configureIosTargets
import com.spoonofcode.convention.configureKotlinAndroid
import com.spoonofcode.convention.libs
import com.spoonofcode.convention.pathToFrameworkName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                applyPlugin("android-application")
                applyPlugin("kotlin-multiplatform")
                applyPlugin("compose-multiplatform")
                applyPlugin("compose-compiler")
                applyPlugin("kotlin-serialization")
            }

            extensions.configure<ApplicationExtension> {
                namespace = "com.spoonofcode.poa"

                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("androidTargetSdk").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }

                configureKotlinAndroid(this)
                configureAndroidCompose(this)
            }

            configureAndroidTarget()
            configureIosTargets(baseName = pathToFrameworkName())

            extensions.configure<KotlinMultiplatformExtension> {
                applyHierarchyTemplate()
            }
        }
    }
}
