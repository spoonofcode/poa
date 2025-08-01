package com.spoonofcode.poa.feature.partner.di

import com.spoonofcode.poa.feature.partner.details.PartnerDetailsViewModel
import com.spoonofcode.poa.feature.partner.overview.PartnerOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val partnerModule = module {
    viewModelOf(::PartnerDetailsViewModel)
    viewModelOf(::PartnerOverviewViewModel)
}