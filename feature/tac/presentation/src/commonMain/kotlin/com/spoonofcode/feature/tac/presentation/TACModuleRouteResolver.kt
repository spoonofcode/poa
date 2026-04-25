package com.spoonofcode.feature.tac.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ModuleRoute
import com.spoonofcode.feature.appnavigation.TACModuleRoute

class TACModuleRouteResolver : ModuleRouteResolver<TACModuleRoute> {
    override fun resolve(moduleRoute: ModuleRoute): Screen? =
        (moduleRoute as? TACModuleRoute)?.let {
            when (it) {
                is TACModuleRoute.TAC -> TACScreen(
                    backNavigationEnable = it.backNavigationEnabled
                )
            }
        }
}