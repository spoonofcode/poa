package com.spoonofcode.feature.auth.presentation.di

import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.AuthModule
import com.spoonofcode.feature.auth.domain.di.authDomainModule
import com.spoonofcode.feature.auth.presentation.AuthModuleRouteResolver
import com.spoonofcode.feature.auth.presentation.login.LoginViewModel
import com.spoonofcode.feature.auth.presentation.logincode.LoginCodeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformAuthPresentationModule: Module

val authPresentationModule: Module = module {
    includes(
        platformAuthPresentationModule,
        authDomainModule,
    )

    factoryOf(::AuthModuleRouteResolver) { bind<ModuleRouteResolver<AuthModule>>() }

    viewModelOf(::LoginViewModel)
    viewModelOf(::LoginCodeViewModel)
}