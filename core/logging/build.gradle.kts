plugins {
    alias(libs.plugins.convention.kmp.library)
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
