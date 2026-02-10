package com.spoonofcode.core.test.base

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.core.test.coroutines.StandardTestDispatcherProvider
import com.spoonofcode.core.test.di.testModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseTest : KoinTest {

    protected var modules: Array<Module> = emptyArray()
    protected val testDispatcher: TestDispatcher by lazy { StandardTestDispatcher() }

    open fun beforeTest() {
        startKoin {
            modules(
                module {
                },
                *modules,
                testModule,
                module {
                    single<DispatcherProvider> { StandardTestDispatcherProvider(testDispatcher) }
                }
            )
        }
        Dispatchers.setMain(testDispatcher)
    }

    open fun afterTest() {
        Dispatchers.resetMain()
        stopKoin()
    }

    protected inline fun <reified T : Any> getSut(): T = get<T>()
}