import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.convention.buildkonfig)
    alias(libs.plugins.convention.cmp.presentation)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.mokkery)
}

kotlin {
    cocoapods {
        version = "1.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        ios.deploymentTarget = "18.0"

        pod("GoogleSignIn")

        framework {
            baseName = "FeatureAuth"
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
                implementation(projects.feature.auth.domain)
                implementation(projects.core.session.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(projects.core.presentationTest)
                implementation(projects.core.recaptcha)
                implementation(projects.feature.auth.dataTest)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.credentials)
                implementation(libs.androidx.credentials.play.service.auth)

                implementation(libs.google.id)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.auth.presentation"
    generateResClass = always
}

buildkonfig {
    packageName = "com.spoonofcode.feature.auth.presentation"

    val localProperties = gradleLocalProperties(rootDir, rootProject.providers)
    val googleIdServerClientId =
        localProperties
            .getProperty("GOOGLE_ID_SERVER_CLIENT_ID_WEB")
            ?: throw IllegalStateException("Missing GOOGLE_ID_SERVER_CLIENT_ID_WEB")

    defaultConfigs {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "GOOGLE_ID_SERVER_CLIENT_ID_WEB",
            value = googleIdServerClientId,
        )
    }
}
