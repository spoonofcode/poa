plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.kotlin.stdlib)

                implementation(projects.feature.profile.domain)
                implementation(projects.core.data)
                implementation(projects.core.network)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}