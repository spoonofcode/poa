package com.spoonofcode.poa.core

import com.spoonofcode.poa.core.data.base.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

class StandardTestDispatcherProvider(
    private val testDispatcher: TestDispatcher
) : DispatcherProvider {
    override fun main(): CoroutineDispatcher = testDispatcher

    override fun io(): CoroutineDispatcher = testDispatcher

    override fun default(): CoroutineDispatcher = testDispatcher
}