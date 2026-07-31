rootProject.name = "poa"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

include(":composeApp")
include(":core:data")
include(":core:firebase:data")
include(":core:firebase:data-test")
include(":core:firebase:domain")
include(":core:network")
include(":core:nfc")
include(":core:presentation")
include(":core:presentation-test")
include(":core:recaptcha")
include(":core:session:data")
include(":core:session:data-test")
include(":core:session:domain")
include(":core:storage:data")
include(":core:test")
include(":feature:appnavigation")
include(":feature:auth:data")
include(":feature:auth:data-test")
include(":feature:auth:domain")
include(":feature:auth:presentation")
include(":feature:faq:presentation")
include(":feature:home:data")
include(":feature:home:data-test")
include(":feature:home:domain")
include(":feature:home:presentation")
include(":feature:notification:data")
include(":feature:notification:data-test")
include(":feature:notification:domain")
include(":feature:notification:presentation")
include(":feature:partner:data")
include(":feature:partner:data-test")
include(":feature:partner:domain")
include(":feature:partner:presentation")
include(":feature:product:data")
include(":feature:product:data-test")
include(":feature:product:domain")
include(":feature:product:presentation")
include(":feature:profile:data")
include(":feature:profile:data-test")
include(":feature:profile:domain")
include(":feature:profile:presentation")
include(":feature:scan-nfc:data")
include(":feature:scan-nfc:domain")
include(":feature:scan-nfc:presentation")
include(":feature:tac:data")
include(":feature:tac:domain")
include(":feature:tac:presentation")
