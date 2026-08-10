package com.spoonofcode.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

val Project.applicationId: String
    get() = libs.findVersion("projectApplicationId").get().toString()

val Project.androidTargetSdk: Int
    get() = libs.findVersion("androidTargetSdk").get().toString().toInt()

val Project.projectVersionCode: Int
    get() = libs.findVersion("projectVersionCode").get().toString().toInt()

val Project.projectVersionName: String
    get() = libs.findVersion("projectVersionName").get().toString()

fun Project.applyPlugin(alias: String) {
    pluginManager.apply(libs.findPlugin(alias).get().get().pluginId)
}
