package com.spoonofcode.core.data.network

data class NetworkConfigImpl(
    override val baseUrl: String = "https://pow-test1-960238188791.europe-west1.run.app",
) : NetworkConfig