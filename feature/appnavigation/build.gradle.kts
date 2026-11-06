plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.kmp.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.bundles.navigation)
            }
        }
    }
}
