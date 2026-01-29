package com.spoonofcode.feature.profile.presentation.di

import com.spoonofcode.feature.profile.data.ProfileRepositoryImpl
import com.spoonofcode.feature.profile.data.remote.RemoteUserDataSource
import com.spoonofcode.feature.profile.domain.repository.ProfileRepository
import com.spoonofcode.feature.profile.domain.usecase.DeleteAccountUseCase
import com.spoonofcode.feature.profile.domain.usecase.GetProfileUseCase
import com.spoonofcode.feature.profile.domain.usecase.LogoutUseCase
import com.spoonofcode.feature.profile.domain.usecase.UpdateProfileUseCase
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewModel
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val profileModule = module {
    singleOf(::RemoteUserDataSource)

    singleOf(::ProfileRepositoryImpl).bind<ProfileRepository>()

    factoryOf(::GetProfileUseCase)
    factoryOf(::DeleteAccountUseCase)
    factoryOf(::UpdateProfileUseCase)
    factoryOf(::LogoutUseCase)

    viewModelOf(::ProfileDetailsViewModel)
    viewModelOf(::ProfileEditViewModel)
}