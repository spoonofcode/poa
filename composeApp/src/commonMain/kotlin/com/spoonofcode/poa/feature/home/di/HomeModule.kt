package com.spoonofcode.poa.feature.home.di

import com.spoonofcode.poa.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}