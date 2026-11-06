package com.spoonofcode.feature.appnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ProfileModuleRoute : ModuleRoute() {
    @Serializable
    object ProfileDetails : ProfileModuleRoute()

    @Serializable
    object ProfileEdit : ProfileModuleRoute()
}
