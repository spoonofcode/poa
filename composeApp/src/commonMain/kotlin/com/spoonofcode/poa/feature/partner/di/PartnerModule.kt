package com.spoonofcode.poa.feature.partner.di

import com.spoonofcode.poa.feature.partner.data.remote.RemotePartnerDataSource
import com.spoonofcode.poa.feature.partner.domain.PartnerDataSource
import com.spoonofcode.poa.feature.partner.domain.usecase.GetPartnerByIdUseCase
import com.spoonofcode.poa.feature.partner.domain.usecase.GetPartnerCategoriesUseCase
import com.spoonofcode.poa.feature.partner.domain.usecase.GetPartnerUsersUseCase
import com.spoonofcode.poa.feature.partner.domain.usecase.GetPartnersFilteredByTextUseCase
import com.spoonofcode.poa.feature.partner.domain.usecase.GetPartnersUseCase
import com.spoonofcode.poa.feature.partner.domain.usecase.UpdatePartnerUseCase
import com.spoonofcode.poa.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewModel
import com.spoonofcode.poa.feature.partner.presentation.details.PartnerDetailsViewModel
import com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewModel
import com.spoonofcode.poa.feature.partner.presentation.overview.PartnerOverviewViewModel
import com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val partnerModule = module {
    singleOf(::RemotePartnerDataSource).bind<PartnerDataSource>()

    factoryOf(::GetPartnerByIdUseCase)
    factoryOf(::GetPartnerCategoriesUseCase)
    factoryOf(::GetPartnersFilteredByTextUseCase)
    factoryOf(::GetPartnersUseCase)
    factoryOf(::GetPartnerUsersUseCase)
    factoryOf(::UpdatePartnerUseCase)

    viewModelOf(::PartnerCustomerOverviewViewModel)
    viewModelOf(::PartnerDetailsViewModel)
    viewModelOf(::PartnerEditViewModel)
    viewModelOf(::PartnerOverviewViewModel)
    viewModelOf(::PartnerPanelViewModel)
    viewModelOf(::PartnerEditViewModel)
}