package com.spoonofcode.feature.home.presentation.di

import com.spoonofcode.feature.home.data.test.di.homeDataTestModule
import com.spoonofcode.feature.home.domain.di.homeDomainModule
import org.koin.dsl.module

val homePresentationTestModule = module {
    includes(
        homeDataTestModule,
        homeDomainModule,
        homePresentationModule,
    )
}