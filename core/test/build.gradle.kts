import dev.mokkery.gradle.ApplicationRule

plugins {
    alias(libs.plugins.convention.buildkonfig)
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.kotlin.test)
                api(libs.koin.test)
                api(libs.kotlinx.coroutines.test)
                api(libs.mokkery.runtime)

                implementation(projects.core.session.domain)
                api(projects.core.data)
            }
        }
    }
}

mokkery {
    rule.set(ApplicationRule.All)
}
