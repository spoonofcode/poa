package com.spoonofcode.poa.core.network.di

import com.spoonofcode.poa.core.network.NetworkConfig
import org.koin.dsl.bind
import org.koin.dsl.module

// localhost for iOS simulator, use your machine's IP for physical device
// For physical device testing with Docker, set isDocker to true and use machine's IP or a domain pointing to it
data class NetworkConfigIOS(
    val isDocker: Boolean = true,
    override val host: String = if (isDocker) "spoonofcode.pl" else "localhost",
    override val port: String = "8443",
    override val baseUrl: String = "https://$host:$port",
) : NetworkConfig

actual val platformNetworkModule = module {
    single { NetworkConfigIOS() } bind NetworkConfig::class
}