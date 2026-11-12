plugins {
    alias(libs.plugins.convention.cmp.application)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.google.services)
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

        xcodeConfigurationToNativeBuildType["Debug-Development"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Release-Development"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["Debug-Production"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Release-Production"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
    }

    sourceSets {
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.splash.screen)
        }
        commonMain.dependencies {
            implementation(libs.navigation3.ui)

            implementation(projects.core.data)
            implementation(projects.core.firebase.data)
            implementation(projects.core.firebase.domain)
            implementation(projects.core.logging)
            implementation(projects.core.network)
            implementation(projects.core.nfc)
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
    }
}

android {
    defaultConfig {
        val releaseVersion = System.getenv("ANDROID_RELEASE_VERSION") ?: "1"
        versionName = releaseVersion
        versionCode =
            if (releaseVersion.contains(".")) {
                val parts = releaseVersion.removePrefix("v").split(".")
                val major = parts.getOrNull(0)?.toIntOrNull() ?: 1
                val minor = parts.getOrNull(1)?.toIntOrNull() ?: 0
                val patch = parts.getOrNull(2)?.toIntOrNull() ?: 0
                major * 10000 + minor * 100 + patch
            } else {
                releaseVersion.toIntOrNull() ?: 1
            }
    }
    signingConfigs {
        val customDebugFile = file("certificates/new-debug.keystore")
        if (customDebugFile.exists()) {
            create("customDebug") {
                storeFile = customDebugFile
                storePassword = "poa123"
                keyAlias = "newdebugkey"
                keyPassword = "poa123"
            }
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
            if (signingConfigs.findByName("customDebug") != null) {
                signingConfig = signingConfigs.getByName("customDebug")
            }
            isDebuggable = true
        }
        getByName("release") {
            isDebuggable = false
            ndk {
                debugSymbolLevel = "full"
            }
            // Dynamically assign the signing config based on the environment
            if (System.getenv("KEYSTORE_PATH") != null) {
                signingConfig = signingConfigs.getByName("ci")
            } else {
                signingConfig = null
            }
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.composeApp"
    generateResClass = always
}
