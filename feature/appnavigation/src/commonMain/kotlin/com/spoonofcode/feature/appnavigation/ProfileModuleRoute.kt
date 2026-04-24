package com.spoonofcode.feature.appnavigation

sealed class ProfileModuleRoute : ModuleRoute() {
    object ProfileDetails : ProfileModuleRoute()
    object ProfileEdit : ProfileModuleRoute()
}