package com.spoonofcode.feature.appnavigation

sealed class FAQModuleRoute : ModuleRoute() {
    object FAQ : FAQModuleRoute()
}