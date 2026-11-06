package com.spoonofcode.core.test.base

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.core.test.coroutines.StandardTestDispatcherProvider
import com.spoonofcode.core.test.di.testModule
import com.spoonofcode.core.test.network.sessionRepositoryMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseTest : KoinTest {
    protected var modules: Array<Module> = emptyArray()
    protected val testDispatcher: TestDispatcher by lazy { StandardTestDispatcher() }
    private var koinApp: KoinApplication? = null

    override fun getKoin(): Koin = koinApp?.koin ?: error("Koin is not initialized. Call super.beforeTest() first.")

    open fun beforeTest() {
        koinApp =
            koinApplication {
                allowOverride(true)
                modules(
                    *modules,
                    testModule,
                    module {
                        single<DispatcherProvider> { StandardTestDispatcherProvider(testDispatcher) }
                        single { sessionRepositoryMock() }
                    },
                )
            }
        Dispatchers.setMain(testDispatcher)
    }

    open fun afterTest() {
        Dispatchers.resetMain()
        koinApp?.close()
        koinApp = null
    }

    protected inline fun <reified T : Any> getSut(): T = get<T>()
}
