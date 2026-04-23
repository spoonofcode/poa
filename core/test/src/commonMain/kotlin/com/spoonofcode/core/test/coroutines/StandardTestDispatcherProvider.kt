package com.spoonofcode.core.test.coroutines

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

class StandardTestDispatcherProvider(
    private val testDispatcher: TestDispatcher
) : DispatcherProvider {
    override fun main(): CoroutineDispatcher = testDispatcher

    override fun  (): CoroutineDispatcher = testDispatcher

    override fun default(): CoroutineDispatcher = testDispatcher
}