package com.spoonofcode.poa.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform