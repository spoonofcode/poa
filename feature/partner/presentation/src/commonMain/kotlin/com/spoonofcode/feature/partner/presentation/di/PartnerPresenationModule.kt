package com.spoonofcode.feature.partner.presentation.di

import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.presentation.PartnerModuleRouteResolver
import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.details.PartnerDetailsViewModel
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditViewModel
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationsViewModel
import com.spoonofcode.feature.partner.presentation.notification.details.PartnerNotificationDetailsViewModel
import com.spoonofcode.feature.partner.presentation.overview.PartnerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val partnerPresentationModule = module {
    factoryOf(::PartnerModuleRouteResolver) { bind<ModuleRouteResolver<PartnerModuleRoute>>() }

    viewModelOf(::PartnerCustomerOverviewViewModel)
    viewModelOf(::PartnerDetailsViewModel)
    viewModelOf(::PartnerEditViewModel)
    viewModelOf(::PartnerOverviewViewModel)
    viewModelOf(::PartnerPanelViewModel)
    viewModelOf(::PartnerEditViewModel)
    viewModelOf(::PartnerNotificationsViewModel)
    viewModelOf(::PartnerNotificationDetailsViewModel)
}