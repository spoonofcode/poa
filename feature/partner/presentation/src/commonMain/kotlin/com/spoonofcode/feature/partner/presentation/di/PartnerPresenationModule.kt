package com.spoonofcode.feature.partner.presentation.di

import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.details.PartnerDetailsViewModel
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditViewModel
import com.spoonofcode.feature.partner.presentation.overview.PartnerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val partnerPresentationModule = module {
    viewModelOf(::PartnerCustomerOverviewViewModel)
    viewModelOf(::PartnerDetailsViewModel)
    viewModelOf(::PartnerEditViewModel)
    viewModelOf(::PartnerOverviewViewModel)
    viewModelOf(::PartnerPanelViewModel)
    viewModelOf(::PartnerEditViewModel)
}