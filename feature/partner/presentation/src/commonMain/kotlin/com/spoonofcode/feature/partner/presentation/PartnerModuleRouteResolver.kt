package com.spoonofcode.feature.partner.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.PartnerModule
import com.spoonofcode.feature.partner.presentation.customer.overview.CustomerOverviewScreen
import com.spoonofcode.feature.partner.presentation.details.PartnerDetailsScreen
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditScreen
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelScreen

class PartnerModuleRouteResolver : ModuleRouteResolver<PartnerModule> {
    override fun resolve(screen: com.spoonofcode.feature.appnavigation.Screen): Screen? =
        (screen as? PartnerModule)?.let {
            when (it) {
                is PartnerModule.CustomerOverviewScreen -> CustomerOverviewScreen(partnerId = it.partnerId)
                is PartnerModule.PartnerDetailsScreen -> PartnerDetailsScreen(partnerId = it.partnerId)
                is PartnerModule.PartnerEditScreen -> PartnerEditScreen(partnerId = it.partnerId)
                PartnerModule.PartnerPanelScreen -> PartnerPanelScreen()
            }
        }
}