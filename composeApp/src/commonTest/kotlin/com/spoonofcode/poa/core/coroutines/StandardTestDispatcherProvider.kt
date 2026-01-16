package com.spoonofcode.poa.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

class StandardTestDispatcherProvider(
    private val testDispatcher: TestDispatcher
) : DispatcherProvider {
    override fun main(): CoroutineDispatcher = testDispatcher

    override fun io(): CoroutineDispatcher = testDispatcher

    override fun default(): CoroutineDispatcher = testDispatcher
}