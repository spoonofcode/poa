package com.spoonofcode.core.presentation.navigation.route

import com.spoonofcode.core.presentation.navigation.Screen

interface ModuleRouteResolver<ScreenType : Screen> {
    fun resolve(screen: Screen): cafe.adriel.voyager.core.screen.Screen? = null
}