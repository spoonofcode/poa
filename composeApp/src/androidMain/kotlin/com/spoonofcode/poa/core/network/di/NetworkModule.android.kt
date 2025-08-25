package com.spoonofcode.poa.core.network.di

import com.spoonofcode.poa.core.network.NetworkConfig
import org.koin.dsl.bind
import org.koin.dsl.module

data class NetworkConfigAndroid(
    val isDocker: Boolean = false,
    override val host: String = if (isDocker) "spoonofcode.pl" else "10.0.2.2",
    override val port: String = "8443",
    override val baseUrl: String = "https://$host:$port",
//    override val baseUrl: String = "https://auth.proof-of-wear.com",
) : NetworkConfig

actual val platformNetworkModule = module {
    single { NetworkConfigAndroid() } bind NetworkConfig::class
}