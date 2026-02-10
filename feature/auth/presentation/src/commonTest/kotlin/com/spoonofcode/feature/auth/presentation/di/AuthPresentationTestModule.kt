package com.spoonofcode.feature.auth.presentation.di

import com.spoonofcode.feature.auth.domain.di.authDomainModule
import com.spoonofcode.feature.auth.presentation.loginRepositoryMock
import org.koin.dsl.module

val authPresentationTestModule = module {
    includes(
        authDomainModule,
        authPresentationModule,
    )
    single { loginRepositoryMock() }
}