package com.spoonofcode.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureAndroidLint(
    commonExtension: CommonExtension
) {
    with(commonExtension) {
        when (this) {
            is com.android.build.api.dsl.ApplicationExtension -> lint {
                abortOnError = true
                checkReleaseBuilds = true
                xmlReport = true
                htmlReport = true
            }
            is com.android.build.api.dsl.LibraryExtension -> lint {
                abortOnError = true
                checkReleaseBuilds = true
                xmlReport = true
                htmlReport = true
            }
        }
    }
}
