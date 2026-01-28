package com.spoonofcode.poa.navigation

import com.spoonofcode.core.presentation.navigation.Screen

sealed class HomeModule : Screen() {
    object HomeScreen : HomeModule()
}