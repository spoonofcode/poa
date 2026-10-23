package com.spoonofcode.core.data

interface Platform {
    val name: String
    val platformType: PlatformType
}

expect fun getPlatform(): Platform

enum class PlatformType {
    ANDROID,
    IOS,
}

val PlatformType.recaptchaName: String
    get() =
        when (this) {
            PlatformType.ANDROID -> "android"
            // In CMP we use Web for iOS
            PlatformType.IOS -> "web"
        }
