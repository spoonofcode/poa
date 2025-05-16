package com.spoonofcode.poa.feature.demo.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.spoonofcode.poa.feature.demo.DemoViewModel

actual val demoModule = module {
    viewModelOf(::DemoViewModel)
}