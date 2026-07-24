plugins {
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kover)
    alias(libs.plugins.google.services)
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

    sourceSets {
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }

        androidMain.dependencies {
            implementation(libs.jetbrains.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.splash.screen)
        }
        commonMain.dependencies {
            implementation(libs.jetbrains.compose.components.resources)
            implementation(libs.jetbrains.compose.ui.tooling.preview)
            implementation(libs.jetbrains.compose.foundation)
            implementation(libs.jetbrains.compose.runtime)
            implementation(libs.jetbrains.compose.material3)
            implementation(libs.jetbrains.compose.ui)

            implementation(compose.materialIconsExtended)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.touchlab.kermit)

            implementation(libs.navigation.compose)
            implementation(libs.navigation3.runtime)
            implementation(libs.navigation3.ui)

            implementation(libs.ksafe)
            implementation(libs.ksafe.compose)

            implementation(projects.core.data)
            implementation(projects.core.firebase.data)
            implementation(projects.core.firebase.domain)
            implementation(projects.core.network)
            implementation(projects.core.nfc)
            implementation(projects.core.presentation)
            implementation(projects.core.recaptcha)
            implementation(projects.core.session.data)
            implementation(projects.core.session.domain)
            implementation(projects.core.storage.data)

            implementation(projects.feature.auth.data)
            implementation(projects.feature.auth.domain)
            implementation(projects.feature.auth.presentation)

            implementation(projects.feature.faq.presentation)

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

            implementation(projects.feature.scanNfc.data)
            implementation(projects.feature.scanNfc.domain)
            implementation(projects.feature.scanNfc.presentation)

            implementation(projects.feature.tac.data)
            implementation(projects.feature.tac.domain)
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

android {
    namespace = "com.spoonofcode.poa"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.spoonofcode.poa"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = (System.getenv("ANDROID_RELEASE_VERSION") ?: "1").toInt()
        versionName = System.getenv("ANDROID_RELEASE_VERSION") ?: "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs {
        create("customDebug") {
            storeFile = file("certificates/new-debug.keystore")
            storePassword = "poa123"
            keyAlias = "newdebugkey"
            keyPassword = "poa123"
        }
        if (System.getenv("KEYSTORE_PATH") != null) {
            create("ci") {
                storeFile = file(System.getenv("KEYSTORE_PATH"))
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASSWORD")
            }
        }
    }
    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("customDebug")
            isDebuggable = true
        }
        getByName("release") {
            // isMinifyEnabled = false
            isDebuggable = false
            // Dynamically assign the signing config based on the environment
            if (System.getenv("KEYSTORE_PATH") != null) {
                signingConfig = signingConfigs.getByName("ci")
            } else {
                signingConfig = null
            }

        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    debugImplementation(libs.jetbrains.compose.ui.tooling)
//    coreLibraryDesugaring(libs.android.desugar.jdk.libs)
}
