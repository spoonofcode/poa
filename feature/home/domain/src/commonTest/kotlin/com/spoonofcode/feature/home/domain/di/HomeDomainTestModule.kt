package com.spoonofcode.feature.home.domain.di

import com.spoonofcode.feature.home.data.test.di.homeDataTestModule
import org.koin.dsl.module

val homeDomainTestModule =
    module {
        includes(
            homeDataTestModule,
            homeDomainModule,
        )
    }
