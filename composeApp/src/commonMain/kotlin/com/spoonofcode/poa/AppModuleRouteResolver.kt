package com.spoonofcode.poa

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.AppModule
import com.spoonofcode.poa.app.MainHostScreen

class AppModuleRouteResolver : ModuleRouteResolver<AppModule> {
    override fun resolve(screen: com.spoonofcode.feature.appnavigation.Screen): Screen? =
        (screen as? AppModule)?.let {
            when (it) {
                AppModule.MainHostScreen -> MainHostScreen()
            }
        }
}