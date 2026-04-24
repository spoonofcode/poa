package com.spoonofcode.core.presentation.navigation.route

import com.spoonofcode.feature.appnavigation.ModuleRoute

class VoyagerRouteResolverImpl(
    private val routeResolver: List<ModuleRouteResolver<out ModuleRoute>>
) : VoyagerRouteResolver {
    override fun resolve(moduleRoute: ModuleRoute): cafe.adriel.voyager.core.screen.Screen {
        return routeResolver.firstNotNullOfOrNull { it.resolve(moduleRoute) }
            ?: error("Resolver for $moduleRoute not found")
    }
}