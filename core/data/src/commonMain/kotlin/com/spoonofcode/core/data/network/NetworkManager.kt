package com.spoonofcode.core.data.network

import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.SharedFlow

interface NetworkManager {
    suspend fun isOnline(): Boolean
    fun observeNetworkState(): SharedFlow<Connectivity.Status>
}