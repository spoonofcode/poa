package com.spoonofcode.poa.core.network

import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.SharedFlow

class NetworkManager {
    private val connectivity = Connectivity {
        autoStart = true
    }

    suspend fun isOnline(): Boolean = connectivity.status() is Connectivity.Status.Connected

    fun observeNetworkState(): SharedFlow<Connectivity.Status> {
        return connectivity.statusUpdates
    }
}