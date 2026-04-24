plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.koin.core)

                implementation(projects.core.data)
                implementation(projects.core.network)
                implementation(projects.feature.auth.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}