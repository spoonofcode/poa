plugins {
    alias(libs.plugins.convention.kmp.domain)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.firebase.domain)
                implementation(projects.core.session.domain)
            }
        }
    }
}
