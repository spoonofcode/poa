package com.spoonofcode.poa.app

import com.spoonofcode.poa.app.di.appModule
import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(
                appModule,
            )
        }
    }
}
