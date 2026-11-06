plugins {
    alias(libs.plugins.convention.buildkonfig)
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.kotlin.cocoapods)
}

kotlin {
    cocoapods {
        version = "1.0"
        summary = "Recaptcha core module"
        homepage = "https://github.com/spoonofcode/poa"
        ios.deploymentTarget = "18.0"

        pod("RecaptchaEnterpriseSDK")

        framework {
            baseName = "CoreRecaptcha"
            isStatic = true
        }

        xcodeConfigurationToNativeBuildType["Debug-Development"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Release-Development"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["Debug-Production"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Release-Production"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.logging)
                implementation(libs.koin.core)
                implementation(libs.kotlinx.coroutines.core)
            }
        }

        commonTest {
            dependencies {
                implementation(projects.core.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.koin.android)
                implementation(libs.recaptcha)
            }
        }

        iosMain {
            dependencies {
            }
        }
    }
}

buildkonfig {
    packageName = "com.spoonofcode.core.recaptcha"

    val recaptchaAndroidDev = "6LdxDkMsAAAAAF927MgxMhsyHubIMlRd9JzFbrb2"
    val recaptchaAndroidProd = "6LejYTUsAAAAAA-suNhCr1DkfzbpcHmZpXWIG-E3"
    val recaptchaIOSDev = "6LdzDkMsAAAAAO5nqrMIeTsEx8iSXed0J4WjCMwb"
    val recaptchaIOSProd = "6LdQ7TksAAAAALlZIObxZXjOQlDAEA8NlGN_Q_hM"

    defaultConfigs {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_ANDROID",
            value = recaptchaAndroidDev,
        )
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_IOS",
            value = recaptchaIOSDev,
        )
    }

    defaultConfigs("development") {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_ANDROID",
            value = recaptchaAndroidDev,
        )
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_IOS",
            value = recaptchaIOSDev,
        )
    }

    defaultConfigs("production") {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_ANDROID",
            value = recaptchaAndroidProd,
        )
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_IOS",
            value = recaptchaIOSProd,
        )
    }
}
