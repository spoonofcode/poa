package com.spoonofcode.feature.appnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppModuleRoute : ModuleRoute() {
    @Serializable
    object MainHost : AppModuleRoute()
}
