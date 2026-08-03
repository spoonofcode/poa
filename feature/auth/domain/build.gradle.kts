plugins {
    alias(libs.plugins.convention.kmp.domain)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.data)
                implementation(projects.core.recaptcha)
                implementation(projects.core.session.domain)
            }
        }
    }
}
