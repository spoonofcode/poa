package com.spoonofcode.feature.auth.data.test.di

import com.spoonofcode.feature.auth.data.test.loginRepositoryMock
import org.koin.dsl.module

val authDataTestModule = module {
    includes(
        module {
            single { loginRepositoryMock() }
        },
    )
}