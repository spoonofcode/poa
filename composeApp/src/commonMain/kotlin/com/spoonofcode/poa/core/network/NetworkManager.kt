package com.spoonofcode.poa.core.network

import com.spoonofcode.poa.core.test.OpenForMokkery
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.SharedFlow

@OpenForMokkery
class NetworkManager(
    private val connectivity: Connectivity
) {

    suspend fun isOnline(): Boolean = connectivity.status() is Connectivity.Status.Connected

    fun observeNetworkState(): SharedFlow<Connectivity.Status> = connectivity.statusUpdates
}