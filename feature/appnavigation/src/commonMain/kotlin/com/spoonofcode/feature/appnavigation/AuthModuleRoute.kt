package com.spoonofcode.feature.appnavigation

sealed class AuthModuleRoute : ModuleRoute() {
    object Login : AuthModuleRoute()
    data class LoginCode(val email: String) : AuthModuleRoute()
}