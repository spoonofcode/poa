package com.spoonofcode.core.presentation.navigation.route

import com.spoonofcode.feature.appnavigation.ModuleRoute

interface VoyagerRouteResolver {
    fun resolve(moduleRoute: ModuleRoute): cafe.adriel.voyager.core.screen.Screen
}