import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.convention.buildkonfig)
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
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

    val localProperties = gradleLocalProperties(rootDir, rootProject.providers)

    val recaptchaDev =
        localProperties
            .getProperty("RECAPTCHA_SITE_KEY_ANDROID_POA_DEV")
            ?: throw IllegalStateException("Missing RECAPTCHA_SITE_KEY_ANDROID_POA_DEV")
    val recaptchaStage =
        localProperties
            .getProperty("RECAPTCHA_SITE_KEY_ANDROID_POA_STAGE")
            ?: throw IllegalStateException("Missing RECAPTCHA_SITE_KEY_ANDROID_POA_STAGE")
    val recaptchaProd =
        localProperties
            .getProperty("RECAPTCHA_SITE_KEY_ANDROID_POA_PROD")
            ?: throw IllegalStateException("Missing RECAPTCHA_SITE_KEY_ANDROID_POA_PROD")
    val recaptchaIOS =
        localProperties
            .getProperty("RECAPTCHA_SITE_KEY_IOS")
            ?: throw IllegalStateException("Missing RECAPTCHA_SITE_KEY_IOS")
    val recaptchaWeb =
        localProperties
            .getProperty("RECAPTCHA_SITE_KEY_WEB")
            ?: throw IllegalStateException("Missing RECAPTCHA_SITE_KEY_WEB")
    val googleIdServerClientId =
        localProperties
            .getProperty("GOOGLE_ID_SERVER_CLIENT_ID_WEB")
            ?: throw IllegalStateException("Missing GOOGLE_ID_SERVER_CLIENT_ID_WEB")

    defaultConfigs {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "RECAPTCHA_SITE_KEY_ANDROID", recaptchaDev)
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "RECAPTCHA_SITE_KEY_IOS", recaptchaIOS)
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "RECAPTCHA_SITE_KEY_WEB", recaptchaWeb)
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "GOOGLE_ID_SERVER_CLIENT_ID_WEB", googleIdServerClientId)
    }

    defaultConfigs("development") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "RECAPTCHA_SITE_KEY_ANDROID", recaptchaDev)
    }

    defaultConfigs("staging") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "RECAPTCHA_SITE_KEY_ANDROID", recaptchaStage)
    }

    defaultConfigs("production") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "RECAPTCHA_SITE_KEY_ANDROID", recaptchaProd)
    }
}
