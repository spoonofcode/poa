import dev.mokkery.gradle.ApplicationRule

plugins {
    alias(libs.plugins.convention.buildkonfig)
    alias(libs.plugins.convention.cmp.presentation)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.kotlin.test)
                implementation(projects.core.session.domain)

                api(libs.koin.test)

                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.coroutines.test)

                // Wystawiamy Mokkery jako api, żeby testy w feature mogły robić verify/every bez dodawania deps.
                api(libs.mokkery.runtime)
            }
        }
    }
}

// Check sourceSet setup https://mokkery.dev/docs/Setup/
mokkery {
    rule.set(ApplicationRule.All)
}
