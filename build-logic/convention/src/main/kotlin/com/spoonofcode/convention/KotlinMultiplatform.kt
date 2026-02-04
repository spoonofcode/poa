package com.spoonofcode.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform() {
    extensions.configure<LibraryExtension> {
        namespace = this@configureKotlinMultiplatform.pathToPackageName()
    }

    configureAndroidTarget()
    configureIosTargets(baseName = pathToFrameworkName())

    extensions.configure<KotlinMultiplatformExtension> {
        applyHierarchyTemplate()

        compilerOptions {
            freeCompilerArgs.add("-Xexpect-actual-classes")
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
            freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
        }
    }
}
