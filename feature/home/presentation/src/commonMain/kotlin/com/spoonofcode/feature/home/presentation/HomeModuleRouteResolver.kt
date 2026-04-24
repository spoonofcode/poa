package com.spoonofcode.feature.home.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.appnavigation.ModuleRoute

class HomeModuleRouteResolver : ModuleRouteResolver<HomeModuleRoute> {
    override fun resolve(moduleRoute: ModuleRoute): Screen? =
        (moduleRoute as? HomeModuleRoute)?.let {
            when (it) {
                HomeModuleRoute.Home -> HomeScreen()
            }
        }
}