plugins {
    alias(libs.plugins.convention.kmp.data)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.session.domain)
            }
        }
    }
}
