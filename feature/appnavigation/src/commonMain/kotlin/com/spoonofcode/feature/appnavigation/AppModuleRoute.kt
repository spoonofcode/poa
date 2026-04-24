package com.spoonofcode.feature.appnavigation

sealed class AppModuleRoute : ModuleRoute() {
    object MainHost : AppModuleRoute()
}