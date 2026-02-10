package com.spoonofcode.feature.appnavigation

sealed class AuthModule : Screen() {
    object LoginScreen : AuthModule()
    data class LoginCodeScreen(
        val email: String,
    ) : AuthModule()
}