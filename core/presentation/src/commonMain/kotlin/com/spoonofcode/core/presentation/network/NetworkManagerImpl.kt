package com.spoonofcode.core.presentation.network

import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.SharedFlow

class NetworkManagerImpl(
    private val connectivity: Connectivity
) : NetworkManager {

    override suspend fun isOnline(): Boolean = connectivity.status() is Connectivity.Status.Connected

    override fun observeNetworkState(): SharedFlow<Connectivity.Status> = connectivity.statusUpdates
}