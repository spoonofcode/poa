package com.spoonofcode.feature.faq.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.FAQModule

class FAQModuleRouteResolver : ModuleRouteResolver<FAQModule> {
    override fun resolve(screen: com.spoonofcode.feature.appnavigation.Screen): Screen? =
        (screen as? FAQModule)?.let {
            when (it) {
                FAQModule.FAQScreen -> FAQScreen()
            }
        }
}