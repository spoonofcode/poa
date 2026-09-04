package com.spoonofcode.feature.partner.presentation.di

import com.spoonofcode.feature.partner.presentation.PartnerEventBus
import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.details.PartnerDetailsViewModel
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditViewModel
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEventBus
import com.spoonofcode.feature.partner.presentation.notification.details.PartnerNotificationDetailsViewModel
import com.spoonofcode.feature.partner.presentation.notification.edit.PartnerNotificationEditViewModel
import com.spoonofcode.feature.partner.presentation.notification.overview.PartnerNotificationsViewModel
import com.spoonofcode.feature.partner.presentation.notification.type.PartnerNotificationTypeViewModel
import com.spoonofcode.feature.partner.presentation.overview.PartnerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val partnerPresentationModule = module {
    singleOf(::PartnerNotificationEventBus)
    singleOf(::PartnerEventBus)
    viewModelOf(::PartnerCustomerOverviewViewModel)
    viewModelOf(::PartnerDetailsViewModel)
    viewModelOf(::PartnerEditViewModel)
    viewModelOf(::PartnerOverviewViewModel)
    viewModelOf(::PartnerPanelViewModel)
    viewModelOf(::PartnerNotificationsViewModel)
    viewModelOf(::PartnerNotificationDetailsViewModel)
    viewModelOf(::PartnerNotificationTypeViewModel)
    viewModelOf(::PartnerNotificationEditViewModel)
    viewModelOf(::PartnerNotificationTypeViewModel)
}
