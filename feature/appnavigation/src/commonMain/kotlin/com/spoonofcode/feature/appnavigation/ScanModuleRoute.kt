package com.spoonofcode.feature.appnavigation

sealed class ScanModuleRoute : ModuleRoute() {
    object Scan : ScanModuleRoute()
}