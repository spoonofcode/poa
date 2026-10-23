package com.spoonofcode.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
    includeAndroidTarget: Boolean = true
) {
    if (includeAndroidTarget) {
        configureAndroidTarget()
    }
    configureIosTargets(baseName = pathToFrameworkName())
    configureKtlint()

    extensions.configure<KotlinMultiplatformExtension> {
        applyHierarchyTemplate()

        compilerOptions {
            freeCompilerArgs.add("-Xexpect-actual-classes")
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
            freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
            freeCompilerArgs.add("-Xcontext-parameters")
        }
    }
}
