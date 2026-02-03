package com.spoonofcode.core.data.network.di

import com.spoonofcode.core.data.network.RemoteRefreshTokenDataSource
import com.spoonofcode.core.data.logging.KermitLogger
import com.spoonofcode.core.data.logging.POALogger
import com.spoonofcode.core.data.network.HttpClientFactory
import com.spoonofcode.core.data.network.NetworkConfig
import com.spoonofcode.core.data.network.NetworkConfigImpl
import com.spoonofcode.core.data.network.NetworkManager
import com.spoonofcode.core.data.network.NetworkManagerImpl
import com.spoonofcode.core.data.network.SessionManager
import com.spoonofcode.core.data.network.SessionManagerImpl
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
    singleOf(::RemoteRefreshTokenDataSource)

    single<POALogger> { KermitLogger }
    single {
        HttpClientFactory(get(), get(), get()).create()
    }
}