import dev.mokkery.gradle.ApplicationRule

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
                api(projects.core.test)
                implementation(projects.core.presentation)

                api(libs.koin.core)
                api(libs.koin.test)

                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.coroutines.test)

                api(libs.mokkery.runtime)

                implementation(libs.connectivity.core)
                implementation(libs.connectivity.device)
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

// Check sourceSet setup https://mokkery.dev/docs/Setup/
mokkery {
    rule.set(ApplicationRule.All)
}
