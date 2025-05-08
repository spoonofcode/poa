package com.spoonofcode.poa.feature.home.di

import com.spoonofcode.poa.feature.home.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val homeModule = module {
    singleOf(::HomeViewModel)
}