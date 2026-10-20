package com.spoonofcode.feature.tac.domain.di

import com.spoonofcode.feature.tac.data.test.di.tacDataTestModule
import org.koin.dsl.module

val tacDomainTestModule = module {
    includes(
        tacDataTestModule,
        tacDomainModule,
    )
}
