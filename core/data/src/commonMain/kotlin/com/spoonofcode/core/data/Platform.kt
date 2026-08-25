package com.spoonofcode.core.data

interface Platform {
    val name: String
    val platformType: PlatformType
}

expect fun getPlatform(): Platform

enum class PlatformType {
    Android,
    iOS,
}

val PlatformType.recaptchaName: String
    get() = when (this) {
        PlatformType.Android -> "android"
        // In CMP we use Web for iOS
        PlatformType.iOS -> "web"
    }
