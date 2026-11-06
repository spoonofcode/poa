package com.spoonofcode.feature.tac.presentation.di

import com.spoonofcode.feature.tac.presentation.TACViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val tacPresentationModule =
    module {
        viewModelOf(::TACViewModel)
    }
