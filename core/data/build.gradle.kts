plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.kmp.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.core.logging)
                api(libs.koin.core)
                api(libs.kotlinx.datetime)
                api(libs.ksafe)
                api(libs.ktor.client.core)
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
