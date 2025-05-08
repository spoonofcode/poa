package com.spoonofcode.poa.core.network.di

import com.spoonofcode.poa.core.network.NetworkConfig
import com.spoonofcode.poa.core.network.NetworkManager
import com.spoonofcode.poa.core.network.SessionManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

data class NetworkConfigIOS(
    override val host: String = "localhost",
    override val port: String = "8443",
    override val baseUrl: String = "https://$host:$port",
) : NetworkConfig

actual val networkModule = module {
    singleOf(::SessionManager)
    singleOf(::NetworkManager)
    single { NetworkConfigIOS() } bind NetworkConfig::class
}