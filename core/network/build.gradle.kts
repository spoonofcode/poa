plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.kmp.serialization)
    alias(libs.plugins.buildkonfig)
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
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "BASE_URL", "https://pow-test1-960238188791.europe-west1.run.app")
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "true")
    }

    defaultConfigs("development") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "BASE_URL", "https://pow-test1-960238188791.europe-west1.run.app")
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "true")
    }

    defaultConfigs("staging") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "BASE_URL", "https://staging-api.poa.spoonofcode.com")
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "true")
    }

    defaultConfigs("production") {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "BASE_URL", "https://api.poa.spoonofcode.com")
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "ENABLE_LOGGING", "false")
    }
}
