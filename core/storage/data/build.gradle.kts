plugins {
    alias(libs.plugins.convention.kmp.android.library)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.ksafe)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.koin.android)
            }
        }
    }
}