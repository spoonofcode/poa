package com.spoonofcode.feature.tac.presentation.di

import com.spoonofcode.core.test.di.coreTestModule
import org.koin.dsl.module

val tacPresentationTestModule = module {
    includes(
        coreTestModule,
        tacPresentationModule,
    )
}