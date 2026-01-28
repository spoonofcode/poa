package com.spoonofcode.core.presentation.navigation.route

import com.spoonofcode.core.presentation.navigation.Screen

class VoyagerRouteResolverImpl(
    private val routeResolver: List<ModuleRouteResolver<out Screen>>
) : VoyagerRouteResolver {
    override fun resolve(screen: Screen): cafe.adriel.voyager.core.screen.Screen {
        return routeResolver.firstNotNullOfOrNull { it.resolve(screen) }
            ?: error("Resolver for $screen not found")
    }
}