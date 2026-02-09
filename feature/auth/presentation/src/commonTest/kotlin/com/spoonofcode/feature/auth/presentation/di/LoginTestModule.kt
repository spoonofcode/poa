package com.spoonofcode.feature.auth.presentation.di

import com.spoonofcode.core.test.di.coreTestModule
import com.spoonofcode.feature.auth.presentation.loginRepositoryMock
import org.koin.dsl.module

val authPresentationTestModule = module {
    includes(
        authPresentationModule,
        coreTestModule,
    )
    single { loginRepositoryMock() }
}