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

    defaultConfigs {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_ANDROID",
            value = recaptchaDev,
        )
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_IOS",
            value = recaptchaIOS,
        )
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_WEB",
            value = recaptchaWeb,
        )
    }

    defaultConfigs("development") {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_ANDROID",
            value = recaptchaDev,
        )
    }

    defaultConfigs("staging") {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_ANDROID",
            value = recaptchaStage,
        )
    }

    defaultConfigs("production") {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_ANDROID",
            value = recaptchaProd,
        )
    }
}
