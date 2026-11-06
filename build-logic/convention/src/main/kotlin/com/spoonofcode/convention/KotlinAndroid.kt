package com.spoonofcode.convention

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension
) {
    with(commonExtension) {
        compileSdk = androidCompileSdk

        defaultConfig.minSdk = androidMinSdk

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
            isCoreLibraryDesugaringEnabled = true
        }

        configureAndroidLint(this)

        configureKotlin()

        dependencies {
            "coreLibraryDesugaring"(libs.findLibrary("android-desugar-jdk-libs").get())
        }
    }
}

internal fun Project.configureKmpAndroidLibraryTarget(
    target: KotlinMultiplatformAndroidLibraryTarget
) {
    with(target) {
        compileSdk = androidCompileSdk
        minSdk = androidMinSdk
        namespace = this@configureKmpAndroidLibraryTarget.pathToPackageName()

        androidResources {
            enable = true
        }
    }

    configureKotlin()
}

internal fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)

            freeCompilerArgs.add(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            )
            freeCompilerArgs.add("-Xcontext-parameters")
        }
    }
}
