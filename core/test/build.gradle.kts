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
                implementation(projects.core.data)
                implementation(projects.core.presentation)
                implementation(projects.feature.appnavigation)

                api(libs.koin.core)
                api(libs.koin.test)

                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.coroutines.test)

                // Wystawiamy Mokkery jako api, żeby testy w feature mogły robić verify/every bez dodawania deps.
                api(libs.mokkery.runtime)

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

kotlin.sourceSets.names.forEach { println("KMP sourceSet: $it") }

// WAŻNE: bo mocks/helpers siedzą w commonMain, a nie w commonTest
mokkery {
//    rule.set(ApplicationRule.Listed("commonMain"))
    // alternatywnie (prościej, ale szerzej):
    // rule.set(ApplicationRule.All)

    rule.set(ApplicationRule.All)
}
