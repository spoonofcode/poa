import dev.mokkery.gradle.ApplicationRule

plugins {
    alias(libs.plugins.convention.kmp.domain)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.profile.domain)
                implementation(projects.core.data)
            }
        }
    }
}

// Check sourceSet setup https://mokkery.dev/docs/Setup/
mokkery {
    rule.set(ApplicationRule.All)
}
