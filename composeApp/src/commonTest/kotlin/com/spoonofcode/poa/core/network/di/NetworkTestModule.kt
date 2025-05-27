package com.spoonofcode.poa.core.network.di

import com.spoonofcode.poa.core.network.networkManagerMock
import com.spoonofcode.poa.core.network.sessionManagerMock
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkTestModule = module {
    singleOf(::sessionManagerMock)
    singleOf(::networkManagerMock)
}