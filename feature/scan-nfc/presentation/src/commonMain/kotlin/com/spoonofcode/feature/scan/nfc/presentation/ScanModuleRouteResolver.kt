package com.spoonofcode.feature.scan.nfc.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ModuleRoute
import com.spoonofcode.feature.appnavigation.ScanModuleRoute

class ScanModuleRouteResolver : ModuleRouteResolver<ScanModuleRoute> {
    override fun resolve(moduleRoute: ModuleRoute): Screen? =
        (moduleRoute as? ScanModuleRoute)?.let {
            when (it) {
                ScanModuleRoute.Scan -> ScanScreen()
            }
        }
}