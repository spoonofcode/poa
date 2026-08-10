package com.spoonofcode.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform() {
    with(pluginManager) {
        applyPlugin("compose-compiler")
        applyPlugin("compose-multiplatform")
    }

    extensions.configure<KotlinMultiplatformExtension> {
        sourceSets.apply {
            getByName("commonMain").dependencies {
                implementation(project(":core:presentation"))
                implementation(libs.findLibrary("koin-compose-viewmodel").get())
            }
        }
    }
}
