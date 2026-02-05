plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // TODO #109 Fix after migration to feature modules
                api(libs.kotlinx.datetime)
                api(libs.ktor.client.core)

                implementation(libs.kotlin.stdlib)

                implementation(libs.connectivity.core)
                implementation(libs.connectivity.device)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                implementation(libs.kotlinx.serialization.json)
                implementation(libs.ktor.client.auth)
                implementation(libs.ktor.client.content.negotiation)

                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.multiplatform.settings)
                implementation(libs.touchlab.kermit)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.security)
                implementation(libs.ktor.client.okhttp)
            }
        }

        iosMain {
            dependencies {
            }
        }
    }

}
