package com.spoonofcode.feature.appnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class HomeModuleRoute : ModuleRoute() {
    @Serializable
    object Home : HomeModuleRoute()
}
