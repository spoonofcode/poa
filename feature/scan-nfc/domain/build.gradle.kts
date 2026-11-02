plugins {
    alias(libs.plugins.convention.kmp.domain)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.network)
            }
        }

        commonTest {
            dependencies {
                implementation(projects.core.test)
            }
        }
    }
}
