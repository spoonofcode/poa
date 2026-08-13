plugins {
    alias(libs.plugins.convention.kmp.domain)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.session.domain)
                implementation(projects.core.firebase.domain)
                implementation(projects.feature.notification.domain)
            }
        }
    }
}
