import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.spoonofcode.convention.buildlogic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.jetbrains.compose)
    compileOnly(libs.compose.gradle.plugin)
    implementation(libs.buildkonfig.gradle.plugin)
    implementation(libs.buildkonfig.compiler)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("cmpApplication") {
            id = libs.plugins.convention.cmp.application.get().pluginId
            implementationClass = "CmpApplicationConventionPlugin"
        }
        register("kmpLibrary") {
            id = libs.plugins.convention.kmp.library.get().pluginId
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("kmpDomain") {
            id = libs.plugins.convention.kmp.domain.get().pluginId
            implementationClass = "KmpDomainConventionPlugin"
        }
        register("kmpData") {
            id = libs.plugins.convention.kmp.data.get().pluginId
            implementationClass = "KmpDataConventionPlugin"
        }
        register("cmpPresentation") {
            id = libs.plugins.convention.cmp.presentation.get().pluginId
            implementationClass = "CmpPresentationConventionPlugin"
        }
        register("kmpSerialization") {
            id = libs.plugins.convention.kmp.serialization.get().pluginId
            implementationClass = "KmpSerializationConventionPlugin"
        }
        register("buildKonfig") {
            id = libs.plugins.convention.buildkonfig.get().pluginId
            implementationClass = "BuildKonfigConventionPlugin"
        }
    }
}