package com.spoonofcode.feature.appnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class TACModuleRoute : ModuleRoute() {
    @Serializable
    data class TAC(
        val backNavigationEnabled: Boolean = true,
    ) : TACModuleRoute()
}
