package com.spoonofcode.feature.auth.presentation.di

import com.spoonofcode.feature.auth.data.test.di.authDataTestModule
import com.spoonofcode.feature.auth.domain.di.authDomainModule
import org.koin.dsl.module

val authPresentationTestModule = module {
    includes(
        authDataTestModule,
        authDomainModule,
        authPresentationModule,
    )
}