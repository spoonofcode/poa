package com.spoonofcode.feature.auth.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.AuthModuleRoute
import com.spoonofcode.feature.appnavigation.ModuleRoute
import com.spoonofcode.feature.auth.presentation.login.LoginScreen
import com.spoonofcode.feature.auth.presentation.logincode.LoginCodeScreen

class AuthModuleRouteResolver : ModuleRouteResolver<AuthModuleRoute> {
    override fun resolve(moduleRoute: ModuleRoute): Screen? =
        (moduleRoute as? AuthModuleRoute)?.let {
            when (it) {
                AuthModuleRoute.Login -> LoginScreen()
                is AuthModuleRoute.LoginCode -> LoginCodeScreen(
                    email = it.email,
                )
            }
        }
}