package com.spoonofcode.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

val Project.applicationId: String
    get() = libs.findVersion("project-applicationId").get().toString()

val Project.androidCompileSdk: Int
    get() = libs.findVersion("android-compileSdk").get().toString().toInt()

val Project.androidMinSdk: Int
    get() = libs.findVersion("android-minSdk").get().toString().toInt()

val Project.androidTargetSdk: Int
    get() = libs.findVersion("android-targetSdk").get().toString().toInt()

val Project.projectVersionCode: Int
    get() = libs.findVersion("project-versionCode").get().toString().toInt()

val Project.projectVersionName: String
    get() = libs.findVersion("project-versionName").get().toString()

fun Project.applyPlugin(alias: String) {
    pluginManager.apply(libs.findPlugin(alias).get().get().pluginId)
}
