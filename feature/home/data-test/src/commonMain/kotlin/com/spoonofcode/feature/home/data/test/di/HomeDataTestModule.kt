package com.spoonofcode.feature.home.data.test.di

import com.spoonofcode.feature.home.data.test.userRepositoryMock
import org.koin.dsl.module

val homeDataTestModule = module {
    includes(
        module {
            single { userRepositoryMock() }
        },
    )
}