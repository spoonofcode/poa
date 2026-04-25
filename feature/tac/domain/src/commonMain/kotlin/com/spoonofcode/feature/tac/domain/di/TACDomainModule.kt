package com.spoonofcode.feature.tac.domain.di

import com.spoonofcode.feature.tac.domain.usecase.GetTACSettingsUseCase
import com.spoonofcode.feature.tac.domain.usecase.UpdateTACSettingsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val tacDomainModule = module {
    factoryOf(::GetTACSettingsUseCase)
    factoryOf(::UpdateTACSettingsUseCase)
}