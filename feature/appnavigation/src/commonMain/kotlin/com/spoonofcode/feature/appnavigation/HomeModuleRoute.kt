package com.spoonofcode.feature.appnavigation

sealed class HomeModuleRoute : ModuleRoute() {
    object Home : HomeModuleRoute()
}