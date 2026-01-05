package com.spoonofcode.poa.feature.login.di

import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginCodeDataSource
import com.spoonofcode.poa.feature.login.data.remote.ktor.KtorRemoteLoginGoogleDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginGoogleDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginRequestCodeDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteRegisterDataSource
import com.spoonofcode.poa.feature.login.domain.LoginCodeDataSource
import com.spoonofcode.poa.feature.login.domain.ktor.KtorLoginGoogleDataSource
import com.spoonofcode.poa.feature.login.domain.LoginDataSource
import com.spoonofcode.poa.feature.login.domain.LoginGoogleDataSource
import com.spoonofcode.poa.feature.login.domain.LoginRequestCodeDataSource
import com.spoonofcode.poa.feature.login.domain.RegisterDataSource
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

    singleOf(::RemoteLoginGoogleDataSource).bind<LoginGoogleDataSource>()
    singleOf(::KtorRemoteLoginGoogleDataSource).bind<KtorLoginGoogleDataSource>()

    singleOf(::RemoteLoginDataSource).bind<LoginDataSource>()
    singleOf(::RemoteRegisterDataSource).bind<RegisterDataSource>()
    singleOf(::RemoteLoginCodeDataSource).bind<LoginCodeDataSource>()
    singleOf(::RemoteLoginRequestCodeDataSource).bind<LoginRequestCodeDataSource>()

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