package com.spoonofcode.feature.tac.presentation.di

import org.koin.dsl.module

val tacPresentationTestModule = module {
    includes(
        tacPresentationModule,
    )
}