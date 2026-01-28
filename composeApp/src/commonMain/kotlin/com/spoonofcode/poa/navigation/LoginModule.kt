package com.spoonofcode.poa.navigation

import com.spoonofcode.core.presentation.navigation.Screen

sealed class LoginModule : Screen() {
    object LoginScreen : LoginModule()
    data class LoginCodeScreen(
        val email: String,
    ) : LoginModule()
}