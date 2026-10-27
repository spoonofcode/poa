package com.spoonofcode.core.network

data class NetworkConfigImpl(
    override val baseUrl: String = BuildKonfig.BASE_URL,
) : NetworkConfig
