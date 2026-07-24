package com.spoonofcode.feature.appnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class FAQModuleRoute : ModuleRoute() {
    @Serializable
    object FAQ : FAQModuleRoute()
}