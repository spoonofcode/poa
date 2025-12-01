package com.spoonofcode.poa.core

import com.spoonofcode.poa.core.data.base.DispatcherProvider
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
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseTest : KoinTest {

    protected var modules: Array<Module> = emptyArray()
    protected val testDispatcher: TestDispatcher by lazy { StandardTestDispatcher() }

    @BeforeTest
    open fun setup() {
        startKoin {
            modules(
                module {
                },
                *modules,
                module {
                    single<DispatcherProvider> { StandardTestDispatcherProvider(testDispatcher) }
                }
            )
        }
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    open fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
    }

    protected inline fun <reified T : Any> getSut(): T = get<T>()

}