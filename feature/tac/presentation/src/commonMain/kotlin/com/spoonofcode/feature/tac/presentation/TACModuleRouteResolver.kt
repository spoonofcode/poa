package com.spoonofcode.feature.tac.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.TACModule

class TACModuleRouteResolver : ModuleRouteResolver<TACModule> {
    override fun resolve(screen: com.spoonofcode.feature.appnavigation.Screen): Screen? =
        (screen as? TACModule)?.let {
            when (it) {
                TACModule.TACScreen -> TACScreen()
            }
        }
}