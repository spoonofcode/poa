package com.spoonofcode.poa.feature.partner.di

import com.spoonofcode.poa.feature.partner.details.PartnerDetailsViewModel
import com.spoonofcode.poa.feature.partner.edit.PartnerEditViewModel
import com.spoonofcode.poa.feature.partner.overview.PartnerOverviewViewModel
import com.spoonofcode.poa.feature.partner.panel.PartnerPanelViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val partnerModule = module {
    viewModelOf(::PartnerDetailsViewModel)
    viewModelOf(::PartnerEditViewModel)
    viewModelOf(::PartnerOverviewViewModel)
    viewModelOf(::PartnerPanelViewModel)
}