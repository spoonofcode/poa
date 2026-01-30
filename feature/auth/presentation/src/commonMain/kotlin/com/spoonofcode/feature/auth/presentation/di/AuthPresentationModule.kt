package com.spoonofcode.feature.auth.presentation.di

import com.spoonofcode.feature.auth.presentation.login.LoginViewModel
import com.spoonofcode.feature.auth.presentation.logincode.LoginCodeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformLoginModule: Module

val authPresentationModule: Module = module {
    includes(platformLoginModule)

    viewModelOf(::LoginViewModel)
    viewModelOf(::LoginCodeViewModel)
}