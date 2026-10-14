plugins {
    alias(libs.plugins.convention.kmp.domain)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonTest {
            dependencies {
                implementation(projects.core.test)
            }
        }
    }
}
