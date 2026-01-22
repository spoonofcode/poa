package com.spoonofcode.poa.core.network.di

import com.spoonofcode.poa.core.logging.KermitLogger
import com.spoonofcode.poa.core.logging.POALogger
import com.spoonofcode.poa.core.network.HttpClientFactory
import com.spoonofcode.poa.core.network.NetworkConfig
import com.spoonofcode.poa.core.network.NetworkConfigImpl
import com.spoonofcode.poa.core.network.NetworkManager
import com.spoonofcode.poa.core.network.NetworkManagerImpl
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.core.network.SessionManagerImpl
import dev.jordond.connectivity.Connectivity
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule: Module = module {
    single {
        Connectivity {
            autoStart = true
        }
    }
    single { NetworkConfigImpl() } bind NetworkConfig::class
    singleOf(::SessionManagerImpl).bind(SessionManager::class)
    singleOf(::NetworkManagerImpl).bind(NetworkManager::class)

    single<POALogger> { KermitLogger }
    single {
        HttpClientFactory(get()).create()
    }
}