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

    val recaptchaAndroidDev = "6LdxDkMsAAAAAF927MgxMhsyHubIMlRd9JzFbrb2"
    val recaptchaAndroidStage = "6LcUEg0uAAAAAO2vBUZ4WxfL2bIqnMdRApYUb0Wo"
    val recaptchaAndroidProd = "6LejYTUsAAAAAA-suNhCr1DkfzbpcHmZpXWIG-E3"
    val recaptchaIOSDev = "6LdzDkMsAAAAAO5nqrMIeTsEx8iSXed0J4WjCMwb"
    val recaptchaIOSProd = "6LdQ7TksAAAAALlZIObxZXjOQlDAEA8NlGN_Q_hM"
    val recaptchaWebDev = "6LeC-kUsAAAAABw2GVwnjheGhOBXThHtibkzwXrQ"

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
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_WEB",
            value = recaptchaWebDev,
        )
    }

    defaultConfigs("development") {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_ANDROID",
            value = recaptchaAndroidDev,
        )
    }

    defaultConfigs("staging") {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_ANDROID",
            value = recaptchaAndroidStage,
        )
    }

    defaultConfigs("production") {
        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "RECAPTCHA_SITE_KEY_ANDROID",
            value = recaptchaAndroidProd,
        )
    }
}
