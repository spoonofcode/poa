package com.spoonofcode.feature.appnavigation

sealed class AppModule : Screen() {
    object MainHostScreen : AppModule()
}