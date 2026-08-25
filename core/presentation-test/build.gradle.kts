plugins {
    alias(libs.plugins.convention.buildkonfig)
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.core.test)
                api(projects.core.presentation)
                api(libs.turbine)
            }
        }
    }
}
