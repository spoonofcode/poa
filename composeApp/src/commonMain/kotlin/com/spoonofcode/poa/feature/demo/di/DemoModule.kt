package com.spoonofcode.poa.feature.demo.di

import com.spoonofcode.poa.feature.demo.presentation.DemoViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val demoModule = module {
    viewModelOf(::DemoViewModel)
}