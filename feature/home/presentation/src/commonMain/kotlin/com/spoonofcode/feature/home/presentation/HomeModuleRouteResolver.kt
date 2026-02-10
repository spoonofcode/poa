package com.spoonofcode.feature.home.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.HomeModule

class HomeModuleRouteResolver : ModuleRouteResolver<HomeModule> {
    override fun resolve(screen: com.spoonofcode.feature.appnavigation.Screen): Screen? =
        (screen as? HomeModule)?.let {
            when (it) {
                HomeModule.HomeScreen -> HomeScreen()
            }
        }
}