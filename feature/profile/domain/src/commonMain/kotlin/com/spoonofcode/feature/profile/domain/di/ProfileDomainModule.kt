package com.spoonofcode.feature.profile.domain.di

import com.spoonofcode.feature.profile.domain.usecase.DeleteAccountUseCase
import com.spoonofcode.feature.profile.domain.usecase.GetProfileUseCase
import com.spoonofcode.feature.profile.domain.usecase.LogoutUseCase
import com.spoonofcode.feature.profile.domain.usecase.UpdateProfileUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileDomainModule = module {
    factoryOf(::GetProfileUseCase)
    factoryOf(::DeleteAccountUseCase)
    factoryOf(::UpdateProfileUseCase)
    factoryOf(::LogoutUseCase)
}