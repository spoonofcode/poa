package com.spoonofcode.poa.core.coroutines.di

import com.spoonofcode.poa.core.coroutines.DispatcherProvider
import org.koin.dsl.module

val coroutinesModule = module {
    single { DispatcherProvider.createDefaultDispatcherProvider() }
}