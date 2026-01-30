
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidLibrary {
        androidResources { enable = true }
    }

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.spoonofcode.core.presentation"
        compileSdk = 36
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "presentationKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        androidMain.dependencies {
            api(libs.ui.tooling.preview)
            api(libs.androidx.activity.compose)
            api(libs.androidx.media3.exoplayer)
            api(libs.androidx.media3.ui.compose)
            api(libs.androidx.security)
            api(libs.androidx.splash.screen)
            api(libs.koin.android)
            api(libs.koin.androidx.compose)
            api(libs.ktor.client.okhttp)

            api(libs.androidx.credentials)
            api(libs.androidx.credentials.play.service.auth)
            api(libs.googleid)
            api(libs.recaptcha)
            api(libs.firebase.messaging)
            api(project.dependencies.platform(libs.firebase.bom))
        }

        commonMain {
            dependencies {
                api(libs.kotlin.stdlib)
                api(libs.components.resources)

                api(libs.compose.icons.fontawesome)
                implementation(libs.compottie)
                implementation(libs.compottie.dot)
                implementation(libs.compottie.network)
                implementation(libs.connectivity.core)
                implementation(libs.connectivity.device)

                api(libs.foundation)
                api(libs.landscapist.coil)
                api(libs.lifecycle.viewmodel.compose)
                api(libs.runtime)
                api(libs.material.icons.core)
                api(libs.material.icons.extended)
                api(libs.material3)
                api(libs.ui)
                api(libs.ui.tooling.preview)
                api(libs.voyager.navigator)
                api(libs.voyager.tab.navigator)
                api(libs.voyager.transitions)

                api(libs.koin.core)
                api(libs.koin.compose)

                // TODO #109 Fix after migration to feature modules
                api(projects.core.data)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.credentials)
                implementation(libs.androidx.credentials.play.service.auth)
                implementation(libs.androidx.media3.exoplayer)
                implementation(libs.androidx.media3.ui.compose)
                implementation(libs.googleid)
                implementation(libs.firebase.messaging)
                implementation(project.dependencies.platform(libs.firebase.bom))
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.junit)
            }
        }

        iosMain {
            dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.core.presentation"
//    generateResClass = always
}