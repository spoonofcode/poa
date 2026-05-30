package com.spoonofcode.feature.partner.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ModuleRoute
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.presentation.customer.overview.CustomerOverviewScreen
import com.spoonofcode.feature.partner.presentation.details.PartnerDetailsScreen
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditScreen
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelScreen

class PartnerModuleRouteResolver : ModuleRouteResolver<PartnerModuleRoute> {
    override fun resolve(moduleRoute: ModuleRoute): Screen? =
        (moduleRoute as? PartnerModuleRoute)?.let {
            when (it) {
                is PartnerModuleRoute.CustomerOverview -> CustomerOverviewScreen()
                is PartnerModuleRoute.PartnerDetails -> PartnerDetailsScreen(partnerId = it.partnerId)
                is PartnerModuleRoute.PartnerEdit -> PartnerEditScreen()
                PartnerModuleRoute.PartnerPanel -> PartnerPanelScreen()
            }
        }
}