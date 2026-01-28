
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
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
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.components.resources)

                implementation(libs.compose.icons.fontawesome)
                implementation(libs.compottie)
                implementation(libs.compottie.dot)
                implementation(libs.compottie.network)
                implementation(libs.connectivity.core)
                implementation(libs.connectivity.device)


                implementation(libs.foundation)
                implementation(libs.landscapist.coil)
                implementation(libs.lifecycle.viewmodel.compose)
                implementation(libs.runtime)
                implementation(libs.material.icons.core)
                implementation(libs.material.icons.extended)
                implementation(libs.material3)
                implementation(libs.ui)
                implementation(libs.ui.tooling.preview)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.tab.navigator)
                implementation(libs.voyager.transitions)

                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                // TODO #109 Fix after migration to feature modules
                implementation(projects.core.data)
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
}