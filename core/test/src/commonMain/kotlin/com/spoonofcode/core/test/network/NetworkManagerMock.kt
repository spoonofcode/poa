package com.spoonofcode.core.test.network

import com.spoonofcode.core.data.network.NetworkManager
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import kotlinx.coroutines.flow.MutableSharedFlow

fun networkManagerMock() = mock<NetworkManager> {
    every { observeNetworkState() } returns MutableSharedFlow(1)
}