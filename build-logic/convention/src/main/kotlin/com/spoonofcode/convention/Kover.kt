package com.spoonofcode.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension

internal fun Project.configureKover() {
    pluginManager.apply("org.jetbrains.kotlinx.kover")

    extensions.configure<KoverProjectExtension> {
        reports {
            total {
                verify {
                    rule {
                        minBound(80)
                    }
                }

                filters {
                    excludes {
                        classes("MainKt")
                        classes("*.MainActivity")

                        // Generated Classes & Resources
                        packages("*.generated.*")
                        packages("*resources*")

                        // Dependency Injection
                        packages("*di*")

                        // Compose Related
                        classes("*ComposeSingletons*")
                        annotatedBy("androidx.compose.runtime.Composable")

                        // Voyager
                        classes("*.*Screen")

                        // ViewStates
                        classes("*.*ViewState")
                    }
                }
            }
        }
    }
}
