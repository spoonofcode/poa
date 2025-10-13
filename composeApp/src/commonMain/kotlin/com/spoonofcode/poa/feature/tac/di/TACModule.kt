package com.spoonofcode.poa.feature.tac.di

import com.spoonofcode.poa.feature.tac.TACViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val tacModule = module {
    viewModelOf(::TACViewModel)
}