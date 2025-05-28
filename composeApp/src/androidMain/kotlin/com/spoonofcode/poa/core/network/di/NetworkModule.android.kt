package com.spoonofcode.poa.core.network.di

import com.spoonofcode.poa.core.network.NetworkConfig
import org.koin.dsl.bind
import org.koin.dsl.module

data class NetworkConfigAndroid(
    override val host: String = "10.0.2.2",
    override val port: String = "8443",
//    override val baseUrl: String = "https://$host:$port",
    override val baseUrl: String = "http://auth.prof-of-wear.com/",
) : NetworkConfig

actual val platformNetworkModule = module {
    single { NetworkConfigAndroid() } bind NetworkConfig::class
}