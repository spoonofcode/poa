package com.spoonofcode.feature.auth.presentation.di

import com.spoonofcode.feature.auth.data.LoginRepositoryImpl
import com.spoonofcode.feature.auth.data.remote.RemoteLoginCodeDataSource
import com.spoonofcode.feature.auth.data.remote.RemoteLoginGoogleDataSource
import com.spoonofcode.feature.auth.data.remote.RemoteLoginRequestCodeDataSource
import com.spoonofcode.core.data.domain.usecase.RemoteRefreshTokenDataSource
import com.spoonofcode.feature.auth.domain.repository.LoginRepository
import com.spoonofcode.feature.auth.domain.usecase.LoginCodeUseCase
import com.spoonofcode.feature.auth.domain.usecase.LoginGoogleUseCase
import com.spoonofcode.feature.auth.domain.usecase.LoginRequestCodeUseCase
import com.spoonofcode.feature.auth.presentation.login.LoginViewModel
import com.spoonofcode.feature.auth.presentation.logincode.LoginCodeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformLoginModule: Module

val loginModule: Module = module {
    includes(platformLoginModule)

    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()

    singleOf(::RemoteLoginGoogleDataSource)
    singleOf(::RemoteRefreshTokenDataSource)

    singleOf(::RemoteLoginCodeDataSource)
    singleOf(::RemoteLoginRequestCodeDataSource)

    factoryOf(::LoginGoogleUseCase)
    factoryOf(::LoginCodeUseCase)
    factoryOf(::LoginRequestCodeUseCase)

    viewModelOf(::LoginViewModel)
    viewModelOf(::LoginCodeViewModel)
}