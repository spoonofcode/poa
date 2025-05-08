package com.spoonofcode.poa.core.network.di

import com.spoonofcode.poa.core.network.NetworkConfig
import com.spoonofcode.poa.core.network.NetworkManager
import com.spoonofcode.poa.core.network.SessionManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

data class NetworkConfigAndroid(
    override val host: String = "10.0.2.2",
    override val port: String = "8443",
    override val baseUrl: String = "https://$host:$port",
) : NetworkConfig

actual val networkModule = module {
    singleOf(::SessionManager)
    singleOf(::NetworkManager)
    single { NetworkConfigAndroid() } bind NetworkConfig::class
}

