plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.buildkonfig)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.koin.core)
                api(libs.touchlab.kermit)
            }
        }
    }
}

buildkonfig {
    packageName = "com.spoonofcode.core.logging"

    defaultConfigs {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "true")
    }

    defaultConfigs("development") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "true")
    }

    defaultConfigs("staging") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "true")
    }

    defaultConfigs("production") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "false")
    }
}
