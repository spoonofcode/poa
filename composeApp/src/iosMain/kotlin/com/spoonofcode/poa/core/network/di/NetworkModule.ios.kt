package com.spoonofcode.poa.core.network.di

import com.spoonofcode.poa.core.network.NetworkConfig
import org.koin.dsl.bind
import org.koin.dsl.module

data class NetworkConfigIOS(
    override val host: String = "localhost",
    override val port: String = "8443",
    override val baseUrl: String = "https://$host:$port",
) : NetworkConfig

actual val platformNetworkModule = module {
    single { NetworkConfigIOS() } bind NetworkConfig::class
}