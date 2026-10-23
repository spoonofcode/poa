package com.spoonofcode.feature.appnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AuthModuleRoute : ModuleRoute() {
    @Serializable
    object Login : AuthModuleRoute()

    @Serializable
    data class LoginCode(
        val email: String,
    ) : AuthModuleRoute()
}
