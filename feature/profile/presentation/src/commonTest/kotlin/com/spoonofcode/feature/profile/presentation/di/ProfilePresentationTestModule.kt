package com.spoonofcode.feature.profile.presentation.di

import com.spoonofcode.feature.profile.data.test.di.profileDataTestModule
import com.spoonofcode.feature.profile.domain.di.profileDomainModule
import org.koin.dsl.module

val profilePresentationTestModule = module {
    includes(
        profileDataTestModule,
        profileDomainModule,
        profilePresentationModule,
    )
}