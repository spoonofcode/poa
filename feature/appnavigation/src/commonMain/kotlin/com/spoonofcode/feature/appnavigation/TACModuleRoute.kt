package com.spoonofcode.feature.appnavigation

sealed class TACModuleRoute : ModuleRoute() {
    object TAC : TACModuleRoute()
}