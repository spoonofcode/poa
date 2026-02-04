import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.buildkonfig)
//    alias(libs.plugins.compose.multiplatform)
//    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.cocoapods)
//    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
//    alias(libs.plugins.mokkery)
    alias(libs.plugins.kover)
//    alias(libs.plugins.google.services)
    alias(libs.plugins.convention.cmp.application)
}

kover {
    reports {
        verify {
            rule {
                minBound(80)
            }
        }

        filters {
            excludes {
                classes("MainKt")
                classes("*.MainActivity")

                // Generated Classes & Resources
                packages("*.generated.*")
                packages("*resources*")

                // Dependecy Injection
                packages("*di*")

                // Compose Related
                classes("*ComposeSingletons*")
                annotatedBy("androidx.compose.runtime.Composable")

                // Voyager
                classes("*.*Screen")

                // ViewStates
                classes("*.*ViewState")
            }
        }
    }
}

kotlin {
    cocoapods {
        version = "1.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        ios.deploymentTarget = "18.0"

        podfile = project.file("../iosApp/Podfile")

        pod("GoogleSignIn")

        framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

//    androidTarget {
//        @OptIn(ExperimentalKotlinGradlePluginApi::class)
//        compilerOptions {
//            jvmTarget.set(JvmTarget.JVM_21)
//        }
//    }
//
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach { iosTarget ->
//        iosTarget.binaries.framework {
//            baseName = "ComposeApp"
//            isStatic = true
//        }
//        iosTarget.binaries.all {
//            // pulls the system framework in and links it
//            linkerOpts("-framework", "CoreNFC")
//        }
//    }

    sourceSets {
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }

        androidMain.dependencies {
            implementation(libs.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.media3.exoplayer)
            implementation(libs.androidx.media3.ui.compose)
            implementation(libs.androidx.security)
            implementation(libs.androidx.splash.screen)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)

            implementation(libs.androidx.credentials)
            implementation(libs.androidx.credentials.play.service.auth)
//            implementation(libs.google.id)
            implementation(libs.recaptcha)
            implementation(libs.firebase.messaging)
            implementation(project.dependencies.platform(libs.firebase.bom))
        }
        commonMain.dependencies {
            implementation(libs.components.resources)
            implementation(libs.ui.tooling.preview)
            implementation(libs.foundation)
            implementation(libs.runtime)
            implementation(libs.material3)
            implementation(libs.ui)

            implementation(libs.compose.icons.fontawesome)
            implementation(libs.compottie)
            implementation(libs.compottie.dot)
            implementation(libs.compottie.network)
            implementation(libs.connectivity.core)
            implementation(libs.connectivity.device)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.landscapist.coil)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.material.icons.core)
            implementation(libs.material.icons.extended)
            implementation(libs.multiplatform.settings)
            implementation(libs.touchlab.kermit)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.tab.navigator)
            implementation(libs.voyager.transitions)

            implementation(projects.core.data)
            implementation(projects.core.nfc)
            implementation(projects.core.presentation)
            implementation(projects.core.recaptcha)

            implementation(projects.feature.auth.data)
            implementation(projects.feature.auth.domain)
            implementation(projects.feature.auth.presentation)

            implementation(projects.feature.home.data)
            implementation(projects.feature.home.domain)
            implementation(projects.feature.home.presentation)

            implementation(projects.feature.notification.data)
            implementation(projects.feature.notification.domain)
            implementation(projects.feature.notification.presentation)

            implementation(projects.feature.partner.data)
            implementation(projects.feature.partner.domain)
            implementation(projects.feature.partner.presentation)

            implementation(projects.feature.product.data)
            implementation(projects.feature.product.domain)
            implementation(projects.feature.product.presentation)

            implementation(projects.feature.profile.data)
            implementation(projects.feature.profile.domain)
            implementation(projects.feature.profile.presentation)

            implementation(projects.feature.scan.data)
            implementation(projects.feature.scan.domain)
            implementation(projects.feature.scan.presentation)

            implementation(projects.feature.tac.presentation)

            implementation(projects.feature.appnavigation)
        }
        commonTest.dependencies {
            implementation(libs.koin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.kotlin.test)
            implementation(libs.turbine)
        }
    }
}

//android {
//    namespace = "com.spoonofcode.poa"
//    compileSdk = libs.versions.androidCompileSdk.get().toInt()
//
//    defaultConfig {
//        applicationId = "com.spoonofcode.poa"
//        minSdk = libs.versions.androidMinSdk.get().toInt()
//        targetSdk = libs.versions.androidTargetSdk.get().toInt()
//        versionCode = 1
//        versionName = "1.0"
//    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
//    signingConfigs {
//        create("customDebug") {
//            storeFile = file("certificates/new-debug.keystore")
//            storePassword = "poa123"
//            keyAlias = "newdebugkey"
//            keyPassword = "poa123"
//        }
//    }
//    buildTypes {
//        getByName("debug") {
//            signingConfig = signingConfigs.getByName("customDebug")
//        }
//    }
////    buildTypes {
////        getByName("release") {
////            isMinifyEnabled = false
////        }
////    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_21
//        targetCompatibility = JavaVersion.VERSION_21
//        isCoreLibraryDesugaringEnabled = true
//    }
//}

dependencies {
    debugImplementation(libs.ui.tooling)
//    coreLibraryDesugaring(libs.android.desugar.jdk.libs)
}