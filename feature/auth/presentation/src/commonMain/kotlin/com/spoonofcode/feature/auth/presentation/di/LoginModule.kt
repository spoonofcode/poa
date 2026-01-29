package com.spoonofcode.feature.auth.presentation.di

import com.spoonofcode.feature.login.data.LoginRepositoryImpl
import com.spoonofcode.feature.login.data.remote.RemoteLoginCodeDataSource
import com.spoonofcode.feature.login.data.remote.RemoteLoginGoogleDataSource
import com.spoonofcode.feature.login.data.remote.RemoteLoginRequestCodeDataSource
import com.spoonofcode.core.data.domain.usecase.RemoteRefreshTokenDataSource
import com.spoonofcode.feature.login.domain.repository.LoginRepository
import com.spoonofcode.feature.login.domain.usecase.LoginCodeUseCase
import com.spoonofcode.feature.login.domain.usecase.LoginGoogleUseCase
import com.spoonofcode.feature.login.domain.usecase.LoginRequestCodeUseCase
import com.spoonofcode.feature.login.presentation.login.LoginViewModel
import com.spoonofcode.feature.login.presentation.logincode.LoginCodeViewModel
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