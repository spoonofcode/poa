package com.spoonofcode.feature.appnavigation

sealed class TACModuleRoute : ModuleRoute() {
    data class TAC(
        val backNavigationEnabled: Boolean = true,
    ) : TACModuleRoute()
}