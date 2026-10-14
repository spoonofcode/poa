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
                        classes("*BuildKonfig*")

                        // Dependency Injection
                        packages("*di*")

                        // Compose Related
                        classes("*ComposableSingletons*")
                        classes("*.ComposableSingletons*")
                        annotatedBy("androidx.compose.runtime.Composable")

                        // CMP Generated Resources & Accessors
                        classes("*Res")
                        classes("*Res$*")
                        classes("*ResourceCollectors*")
                        classes("*String0*")
                        classes("*Drawable0*")
                        classes("*Font0*")
                        classes("*Color0*")

                        // Voyager
                        classes("*.*Screen")

                        // ViewStates & ViewActions
                        classes("*.*ViewState")
                        classes("*.*ViewAction")
                        classes("*ViewAction*")

                        // Navigation & Routes
                        classes("*.*Route")
                        classes("*Route*")

                        // Models & Repositories
                        packages("*model*")
                        packages("*repository*")
                        classes("*Model*")
                        classes("*Settings*")
                        classes("*Repository*")

                        // Network DTOs (Requests & Responses)
                        classes("*.*Response")
                        classes("*.*Request")
                    }
                }
            }
        }
    }
}
