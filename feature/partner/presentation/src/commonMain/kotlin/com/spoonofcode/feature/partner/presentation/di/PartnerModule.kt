package com.spoonofcode.feature.partner.presentation.di

import com.spoonofcode.feature.partner.data.PartnerRepositoryImpl
import com.spoonofcode.feature.partner.data.remote.RemotePartnerCategoryDataSource
import com.spoonofcode.feature.partner.data.remote.RemotePartnerDataSource
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerByIdUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerCategoriesUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerUsersUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersFilteredByPartnerCategoryUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersFilteredByTextUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersUseCase
import com.spoonofcode.feature.partner.domain.usecase.UpdatePartnerUseCase
import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.details.PartnerDetailsViewModel
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditViewModel
import com.spoonofcode.feature.partner.presentation.overview.PartnerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val partnerModule = module {
    singleOf(::RemotePartnerDataSource)
    singleOf(::RemotePartnerCategoryDataSource)

    singleOf(::PartnerRepositoryImpl).bind<PartnerRepository>()

    factoryOf(::GetPartnerByIdUseCase)
    factoryOf(::GetPartnerCategoriesUseCase)
    factoryOf(::GetPartnersFilteredByPartnerCategoryUseCase)
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