package com.spoonofcode.feature.appnavigation

sealed class HomeModule : Screen() {
    object HomeScreen : HomeModule()
}