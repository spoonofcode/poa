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