import dev.mokkery.gradle.ApplicationRule

plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.koin.core)

                implementation(projects.core.firebase.data)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

// Check sourceSet setup https://mokkery.dev/docs/Setup/
mokkery {
    rule.set(ApplicationRule.All)
}