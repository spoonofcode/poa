package com.spoonofcode.feature.profile.domain.di

import com.spoonofcode.feature.profile.data.test.di.profileDataTestModule
import org.koin.dsl.module

val profileDomainTestModule = module {
    includes(
        profileDataTestModule,
        profileDomainModule,
    )
}
