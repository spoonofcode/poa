package com.spoonofcode.feature.faq.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.FAQModuleRoute
import com.spoonofcode.feature.appnavigation.ModuleRoute

class FAQModuleRouteResolver : ModuleRouteResolver<FAQModuleRoute> {
    override fun resolve(moduleRoute: ModuleRoute): Screen? =
        (moduleRoute as? FAQModuleRoute)?.let {
            when (it) {
                FAQModuleRoute.FAQ -> FAQScreen()
            }
        }
}