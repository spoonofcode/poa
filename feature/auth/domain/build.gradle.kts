plugins {
    alias(libs.plugins.convention.kmp.domain)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.recaptcha)
                implementation(projects.core.session.domain)
                implementation(projects.core.data)
            }
        }
        commonTest {
            dependencies {
                implementation(projects.core.test)
                implementation(projects.feature.auth.dataTest)
            }
        }
    }
}
