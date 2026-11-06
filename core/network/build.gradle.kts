plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.kmp.serialization)
    alias(libs.plugins.convention.buildkonfig)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.bundles.ktor)
                implementation(projects.core.logging)
                implementation(projects.core.data)
                implementation(projects.core.session.domain)
            }
        }
    }
}

buildkonfig {
    packageName = "com.spoonofcode.core.network"

    defaultConfigs {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "BASE_URL", "https://testing.proof-of-wear.com")
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "true")
    }

    defaultConfigs("development") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "BASE_URL", "https://testing.proof-of-wear.com")
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "true")
    }

    defaultConfigs("production") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "BASE_URL", "https://auth.proof-of-wear.com")
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "false")
    }
}
