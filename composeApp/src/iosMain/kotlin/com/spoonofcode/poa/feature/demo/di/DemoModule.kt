package com.spoonofcode.poa.feature.demo.di

import com.spoonofcode.poa.feature.demo.DemoViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val demoModule = module {
    singleOf(::DemoViewModel)
}