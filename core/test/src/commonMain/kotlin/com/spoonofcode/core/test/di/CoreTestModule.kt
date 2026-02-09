package com.spoonofcode.core.test.di

import com.spoonofcode.core.test.network.networkManagerMock
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreTestModule = module {
    singleOf(::networkManagerMock)
}