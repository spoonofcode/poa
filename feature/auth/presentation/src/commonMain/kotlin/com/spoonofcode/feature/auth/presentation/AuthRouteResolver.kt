package com.spoonofcode.feature.auth.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.AuthModule
import com.spoonofcode.feature.auth.presentation.login.LoginScreen
import com.spoonofcode.feature.auth.presentation.logincode.LoginCodeScreen

class AuthRouteResolver : ModuleRouteResolver<AuthModule> {
    override fun resolve(screen: com.spoonofcode.feature.appnavigation.Screen): Screen? =
        (screen as? AuthModule)?.let {
            when (it) {
                AuthModule.LoginScreen -> LoginScreen()
                is AuthModule.LoginCodeScreen -> LoginCodeScreen(
                    it.email
                )
            }
        }
}