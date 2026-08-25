plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.kmp.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.bundles.ktor)
                implementation(projects.core.logging)
                implementation(projects.core.data)
                implementation(projects.core.session.domain)
            }
        }
    }
}
