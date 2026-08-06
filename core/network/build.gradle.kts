plugins {
    alias(libs.plugins.convention.kmp.android.library)
    alias(libs.plugins.convention.kmp.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.ktor.client.auth)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.serialization.kotlinx.json)

                implementation(projects.core.data)
                implementation(projects.core.session.domain)
            }
        }
    }
}
