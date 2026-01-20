package com.spoonofcode.poa.navigation

sealed class LoginModule : Screen() {
    object LoginScreen : LoginModule()
    data class LoginCodeScreen(
        val email: String,
    ) : LoginModule()
}