package com.spoonofcode.feature.home.presentation.di

import com.spoonofcode.core.test.di.coreTestModule
import com.spoonofcode.feature.home.domain.di.homeDomainModule
import org.koin.dsl.module

val homePresentationTestModule = module {
    includes(
        coreTestModule,
        homeDomainModule,
        homePresentationModule,
    )
}