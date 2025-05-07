package com.spoonofcode.poa.feature.home.di

import com.spoonofcode.poa.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val homeModule = module {
    viewModelOf(::HomeViewModel)
}