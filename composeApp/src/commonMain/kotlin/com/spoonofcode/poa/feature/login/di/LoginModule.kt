package com.spoonofcode.poa.feature.login.di

import com.spoonofcode.poa.feature.login.data.LoginRepositoryImpl
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginCodeDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginGoogleDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginRequestCodeDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteRegisterDataSource
import com.spoonofcode.poa.feature.login.data.remote.ktor.KtorRemoteLoginGoogleDataSource
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository
import com.spoonofcode.poa.feature.login.domain.usecase.LoginCodeUseCase
import com.spoonofcode.poa.feature.login.domain.usecase.LoginGoogleUseCase
import com.spoonofcode.poa.feature.login.domain.usecase.LoginRequestCodeUseCase
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

    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()

    singleOf(::RemoteLoginGoogleDataSource)
    singleOf(::KtorRemoteLoginGoogleDataSource)

    singleOf(::RemoteLoginDataSource)
    singleOf(::RemoteRegisterDataSource)
    singleOf(::RemoteLoginCodeDataSource)
    singleOf(::RemoteLoginRequestCodeDataSource)

    factoryOf(::LoginUseCase)
    factoryOf(::LoginGoogleUseCase)
    factoryOf(::LoginCodeUseCase)
    factoryOf(::LoginRequestCodeUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::RegisterUseCase)

    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}