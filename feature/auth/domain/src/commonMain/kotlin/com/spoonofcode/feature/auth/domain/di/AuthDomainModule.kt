package com.spoonofcode.feature.auth.domain.di

import com.spoonofcode.feature.auth.domain.usecase.LoginCodeUseCase
import com.spoonofcode.feature.auth.domain.usecase.LoginGoogleUseCase
import com.spoonofcode.feature.auth.domain.usecase.LoginRequestCodeUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authDomainModule: Module =
    module {
        factoryOf(::LoginGoogleUseCase)
        factoryOf(::LoginCodeUseCase)
        factoryOf(::LoginRequestCodeUseCase)
    }
