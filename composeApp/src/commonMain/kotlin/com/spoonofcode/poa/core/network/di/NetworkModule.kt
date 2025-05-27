package com.spoonofcode.poa.core.network.di

import com.spoonofcode.poa.core.network.NetworkManager
import com.spoonofcode.poa.core.network.SessionManager
import dev.jordond.connectivity.Connectivity
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val platformNetworkModule: Module

val networkModule: Module = module {
    includes(platformNetworkModule)
    single {
        Connectivity {
            autoStart = true
        }
    }
    singleOf(::SessionManager)
    singleOf(::NetworkManager)
}