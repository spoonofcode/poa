package com.spoonofcode.poa.feature.login.di

import com.spoonofcode.poa.feature.login.data.remote.LoginGoogleRemoteDataSource
import com.spoonofcode.poa.feature.login.data.remote.LoginRemoteDataSource
import com.spoonofcode.poa.feature.login.data.remote.RegisterRemoteDataSource
import com.spoonofcode.poa.feature.login.domain.LoginDataSource
import com.spoonofcode.poa.feature.login.domain.LoginGoogleDataSource
import com.spoonofcode.poa.feature.login.domain.RegisterDataSource
import com.spoonofcode.poa.feature.login.domain.usecase.LoginGoogleUseCase
import com.spoonofcode.poa.feature.login.domain.usecase.LoginUseCase
import com.spoonofcode.poa.feature.login.domain.usecase.LogoutUseCase
import com.spoonofcode.poa.feature.login.domain.usecase.RegisterUseCase
import com.spoonofcode.poa.feature.login.presentation.forgotPassword.ForgotPasswordViewModel
import com.spoonofcode.poa.feature.login.presentation.login.LoginViewModel
import com.spoonofcode.poa.feature.login.presentation.register.RegisterViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformLoginModule: Module

val loginModule: Module = module {
    includes(platformLoginModule)

    singleOf(::LoginGoogleRemoteDataSource).bind<LoginGoogleDataSource>()
    singleOf(::LoginRemoteDataSource).bind<LoginDataSource>()
    singleOf(::RegisterRemoteDataSource).bind<RegisterDataSource>()

    factoryOf(::LoginUseCase)
    factoryOf(::LoginGoogleUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::RegisterUseCase)

    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}