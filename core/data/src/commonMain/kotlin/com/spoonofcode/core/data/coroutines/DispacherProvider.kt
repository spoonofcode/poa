package com.spoonofcode.core.data.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

interface DispatcherProvider {
    /**
     * Returns a scheduler for work that should be executed on Android's main thread
     */
    fun main(): CoroutineDispatcher

    /**
     * Returns a dispatcher for heavy-weight, io-bound work
     */
    fun io(): CoroutineDispatcher

    /**
     * Returns a dispatcher for light-weight, non-io work
     */
    fun default(): CoroutineDispatcher

    companion object Factory {
        @Suppress("FunctionMaxLength")
        fun createDefaultDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
    }
}

internal class DefaultDispatcherProvider : DispatcherProvider {
    override fun main(): CoroutineDispatcher = Dispatchers.Main

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun default(): CoroutineDispatcher = Dispatchers.Default
}