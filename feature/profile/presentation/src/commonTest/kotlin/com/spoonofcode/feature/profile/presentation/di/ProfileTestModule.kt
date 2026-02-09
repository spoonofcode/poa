package com.spoonofcode.feature.profile.presentation.di

import com.spoonofcode.core.test.di.coreTestModule
import com.spoonofcode.feature.profile.domain.di.profileDomainModule
import com.spoonofcode.feature.profile.presentation.profileRepositoryMock
import org.koin.dsl.module

val profileTestModule = module {
    includes(
        module {
            single { profileRepositoryMock() }
        },
        coreTestModule,
        profileDomainModule,
        profilePresentationModule,
    )
}