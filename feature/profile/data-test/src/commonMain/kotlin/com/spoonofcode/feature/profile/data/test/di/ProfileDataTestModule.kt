package com.spoonofcode.feature.profile.data.test.di

import com.spoonofcode.feature.profile.data.test.profileRepositoryMock
import org.koin.dsl.module

val profileDataTestModule = module {
    includes(
        module {
            single { profileRepositoryMock() }
        },
    )
}