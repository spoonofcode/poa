package com.spoonofcode.core.test.network

import com.spoonofcode.core.presentation.network.NetworkManager
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class FakeNetworkManager : NetworkManager {
    override suspend fun isOnline(): Boolean = true
    override fun observeNetworkState(): SharedFlow<Connectivity.Status> = MutableSharedFlow(1)
}