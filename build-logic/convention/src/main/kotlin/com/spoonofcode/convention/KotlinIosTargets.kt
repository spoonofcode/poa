package com.spoonofcode.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureIosTargets(
    baseName: String? = null
) {
    extensions.configure<KotlinMultiplatformExtension> {
        val targets = listOf(
            iosArm64(),
            iosSimulatorArm64()
        )

        baseName?.let { name ->
            targets.forEach { target ->
                target.binaries.framework {
                    this.baseName = name
                    this.isStatic = true
                }
            }
        }
    }
}