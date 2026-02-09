package com.spoonofcode.core.presentation.network

import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.SharedFlow

interface NetworkManager {
    suspend fun isOnline(): Boolean
    fun observeNetworkState(): SharedFlow<Connectivity.Status>
}