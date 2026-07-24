package com.spoonofcode.feature.auth.presentation.di

import com.spoonofcode.feature.auth.domain.di.authDomainModule
import com.spoonofcode.feature.auth.presentation.login.LoginViewModel
import com.spoonofcode.feature.auth.presentation.logincode.LoginCodeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformAuthPresentationModule: Module

val authPresentationModule: Module = module {
    includes(
        platformAuthPresentationModule,
        authDomainModule,
    )

    viewModelOf(::LoginViewModel)
    viewModelOf(::LoginCodeViewModel)
}