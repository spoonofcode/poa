package com.spoonofcode.core.presentation.navigation.route

import com.spoonofcode.feature.appnavigation.ModuleRoute

interface ModuleRouteResolver<ScreenType : ModuleRoute> {
    fun resolve(moduleRoute: ModuleRoute): cafe.adriel.voyager.core.screen.Screen? = null
}