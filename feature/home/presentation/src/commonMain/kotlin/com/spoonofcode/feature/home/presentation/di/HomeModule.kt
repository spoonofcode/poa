package com.spoonofcode.feature.home.presentation.di

import com.spoonofcode.poa.feature.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}