plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)

                api(libs.connectivity.core)
                api(libs.connectivity.device)
                api(libs.koin.core)
                api(libs.koin.compose)
                api(libs.koin.compose.viewmodel)
                api(libs.kotlinx.datetime)
                api(libs.kotlinx.serialization.json)
                api(libs.ktor.client.auth)
                api(libs.ktor.client.content.negotiation)
                api(libs.ktor.client.core)
                api(libs.ktor.client.logging)
                api(libs.ktor.serialization.kotlinx.json)
                api(libs.multiplatform.settings)
                api(libs.touchlab.kermit)
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
                implementation(libs.recaptcha)
                implementation(libs.firebase.messaging)
                implementation(project.dependencies.platform(libs.firebase.bom))
            }
        }

        iosMain {
            dependencies {
            }
        }
    }

}