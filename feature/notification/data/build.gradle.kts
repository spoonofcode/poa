plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.koin.core)
                implementation(projects.feature.notification.domain)
                implementation(libs.multiplatform.settings)

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