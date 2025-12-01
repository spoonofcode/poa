package com.spoonofcode.poa.core.data.base.di

import com.spoonofcode.poa.core.data.base.DispatcherProvider
import org.koin.dsl.module

val coreBaseModule = module {
    single { DispatcherProvider.createDefaultDispatcherProvider() }
}