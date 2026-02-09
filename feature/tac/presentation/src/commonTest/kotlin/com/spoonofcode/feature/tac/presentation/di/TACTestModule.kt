package com.spoonofcode.feature.tac.presentation.di

import com.spoonofcode.core.presentation.di.uiModule
import org.koin.dsl.module

val tacTestModule = module {
    includes(
        tacPresentationModule,
        uiModule,
    )
}