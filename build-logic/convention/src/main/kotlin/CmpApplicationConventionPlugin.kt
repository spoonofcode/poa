import com.android.build.api.dsl.ApplicationExtension
import com.spoonofcode.convention.androidTargetSdk
import com.spoonofcode.convention.applicationId
import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.configureAndroidCompose
import com.spoonofcode.convention.configureComposeMultiplatform
import com.spoonofcode.convention.configureKover
import com.spoonofcode.convention.configureKotlinAndroid
import com.spoonofcode.convention.configureKotlinMultiplatform
import com.spoonofcode.convention.projectVersionCode
import com.spoonofcode.convention.projectVersionName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class CmpApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                applyPlugin("android-application")
                applyPlugin("kotlin-multiplatform")
                applyPlugin("convention-kmp-serialization")
            }

            configureKotlinMultiplatform()
            configureComposeMultiplatform()
            configureKover()

            extensions.configure<ApplicationExtension> {
                namespace = "com.spoonofcode.poa"

                defaultConfig {
                    applicationId = target.applicationId
                    targetSdk = target.androidTargetSdk
                    versionCode = target.projectVersionCode
                    versionName = target.projectVersionName
                }

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro",
                        )
                    }
                }

                configureKotlinAndroid(this)
                configureAndroidCompose(this)
            }
        }
    }
}
