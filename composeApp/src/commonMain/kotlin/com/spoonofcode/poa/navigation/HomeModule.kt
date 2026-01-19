package com.spoonofcode.poa.navigation

sealed class HomeModule : Screen() {
    object HomeScreen : HomeModule()
}