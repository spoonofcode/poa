plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.navigation.compose)
                implementation(libs.navigation3.runtime)
                implementation(libs.navigation3.ui)
            }
        }
    }
}