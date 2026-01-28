package com.spoonofcode.core.data.coroutines.di

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import org.koin.dsl.module

val coroutinesModule = module {
    single { DispatcherProvider.createDefaultDispatcherProvider() }
}