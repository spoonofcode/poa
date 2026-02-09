plugins {
    alias(libs.plugins.convention.buildkonfig)
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.kotlin.test)
                implementation(projects.core.data)
                implementation(projects.core.presentation)
                implementation(projects.feature.appnavigation)

                api(libs.koin.core)
                api(libs.koin.test)

                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.coroutines.test)

                api(libs.mokkery)

                implementation(libs.connectivity.core)
                implementation(libs.connectivity.device)
                implementation(libs.multiplatform.settings)
                implementation("com.russhwolf:multiplatform-settings-test:1.3.0")
            }
        }

        commonTest {
            dependencies {
                api(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
            }
        }

        iosMain {
            dependencies {
            }
        }
    }
}
