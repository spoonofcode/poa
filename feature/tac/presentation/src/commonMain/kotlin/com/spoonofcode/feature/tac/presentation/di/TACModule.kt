package com.spoonofcode.feature.tac.presentation.di

import com.spoonofcode.poa.feature.tac.presentation.TACViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val tacModule = module {
    viewModelOf(::TACViewModel)
}