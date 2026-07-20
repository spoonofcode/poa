package com.spoonofcode.feature.appnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScanModuleRoute : ModuleRoute() {
    @Serializable
    object Scan : ScanModuleRoute()
}