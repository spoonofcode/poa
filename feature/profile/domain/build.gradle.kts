plugins {
    alias(libs.plugins.convention.kmp.domain)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.session.domain)
            }
        }
        commonTest {
            dependencies {
                implementation(projects.core.test)
                implementation(projects.feature.profile.dataTest)
            }
        }
    }
}
