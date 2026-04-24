package com.spoonofcode.poa

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.AppModuleRoute
import com.spoonofcode.feature.appnavigation.ModuleRoute
import com.spoonofcode.poa.app.MainHostScreen

class AppModuleRouteResolver : ModuleRouteResolver<AppModuleRoute> {
    override fun resolve(moduleRoute: ModuleRoute): Screen? =
        (moduleRoute as? AppModuleRoute)?.let {
            when (it) {
                AppModuleRoute.MainHost -> MainHostScreen()
            }
        }
}