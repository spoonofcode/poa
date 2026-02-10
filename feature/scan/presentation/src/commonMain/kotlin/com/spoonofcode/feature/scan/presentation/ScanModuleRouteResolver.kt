package com.spoonofcode.feature.scan.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ScanModule

class ScanModuleRouteResolver : ModuleRouteResolver<ScanModule> {
    override fun resolve(screen: com.spoonofcode.feature.appnavigation.Screen): Screen? =
        (screen as? ScanModule)?.let {
            when (it) {
                ScanModule.ScanScreen -> ScanScreen()
            }
        }
}