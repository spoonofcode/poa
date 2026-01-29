package com.spoonofcode.feature.partner.domain.di

import com.spoonofcode.feature.partner.domain.usecase.GetPartnerByIdUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerCategoriesUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersFilteredByPartnerCategoryUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersFilteredByTextUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val partnerDomainModule = module {
    factoryOf(::GetPartnerByIdUseCase)
    factoryOf(::GetPartnerCategoriesUseCase)
    factoryOf(::GetPartnersFilteredByPartnerCategoryUseCase)
    factoryOf(::GetPartnersFilteredByTextUseCase)
    factoryOf(::GetPartnersUseCase)
    factoryOf(::GetPartnerUsersUseCase)
    factoryOf(::UpdatePartnerUseCase)
}