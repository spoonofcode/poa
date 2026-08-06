plugins {
    alias(libs.plugins.convention.kmp.android.library)
    alias(libs.plugins.convention.kmp.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.kotlinx.datetime)
                api(libs.ktor.client.core)
                api(libs.koin.core)
                api(libs.lifecycle.viewmodel)

                implementation(libs.touchlab.kermit)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.security)
                implementation(libs.ktor.client.okhttp)
            }
        }
    }
}
