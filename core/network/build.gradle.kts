plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.kmp.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.bundles.ktor)

                // ZOBACZ JAK MOŻNA TO USUNAC
                implementation(projects.core.data)
                implementation(projects.core.session.domain)
            }
        }
    }
}
