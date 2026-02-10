package com.spoonofcode.core.presentation.test.network

import com.spoonofcode.core.presentation.network.NetworkManager
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import kotlinx.coroutines.flow.MutableSharedFlow

internal fun networkManagerMock() = mock<NetworkManager> {
    every { observeNetworkState() } returns MutableSharedFlow(1)
}