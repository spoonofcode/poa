package com.spoonofcode.poa.feature.profile.di

import com.spoonofcode.poa.feature.profile.domain.usecase.GetProfileUseCase
import com.spoonofcode.poa.feature.profile.domain.usecase.UpdateProfileUseCase
import com.spoonofcode.poa.feature.profile.presentation.details.ProfileDetailsViewModel
import com.spoonofcode.poa.feature.profile.presentation.edit.ProfileEditViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profileModule = module {
    factoryOf(::GetProfileUseCase)
    factoryOf(::UpdateProfileUseCase)

    viewModelOf(::ProfileDetailsViewModel)
    viewModelOf(::ProfileEditViewModel)
}