package com.spoonofcode.poa.core.ui.navigation.route

import com.spoonofcode.poa.navigation.Screen


interface ModuleRouteResolver<ScreenType : Screen> {
    fun resolve(screen: Screen): cafe.adriel.voyager.core.screen.Screen? = null
}