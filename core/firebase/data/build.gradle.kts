plugins {
    alias(libs.plugins.convention.kmp.data)
    alias(libs.plugins.kotlin.cocoapods)
}

kotlin {
    cocoapods {
        version = "1.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        ios.deploymentTarget = "18.0"

//        podfile = project.file("../../../iosApp/Podfile")

        pod("FirebaseCore")
        pod("FirebaseMessaging")

        framework {
            baseName = "FeatureFirebase"
            isStatic = true
        }

        xcodeConfigurationToNativeBuildType["Debug-Development"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Release-Development"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["Debug-Staging"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Release-Staging"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["Debug-Production"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Release-Production"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)

                implementation(projects.core.firebase.domain)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.firebase.messaging)
                implementation(project.dependencies.platform(libs.firebase.bom))
            }
        }
    }
}
