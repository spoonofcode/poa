rootProject.name = "poa"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

//plugins {
//    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
//}

include(":composeApp")
include(":core:data")
include(":core:nfc")
include(":core:presentation")
include(":core:recaptcha")
include(":feature:appnavigation")
include(":feature:auth:data")
include(":feature:auth:domain")
include(":feature:auth:presentation")
include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:presentation")
include(":feature:notification:data")
include(":feature:notification:domain")
include(":feature:notification:presentation")
include(":feature:partner:data")
include(":feature:partner:domain")
include(":feature:partner:presentation")
include(":feature:product:data")
include(":feature:product:domain")
include(":feature:product:presentation")
include(":feature:profile:data")
include(":feature:profile:domain")
include(":feature:profile:presentation")
include(":feature:scan:data")
include(":feature:scan:domain")
include(":feature:scan:presentation")
include(":feature:tac:presentation")
