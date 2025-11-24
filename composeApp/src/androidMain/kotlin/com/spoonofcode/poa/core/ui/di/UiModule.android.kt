package com.spoonofcode.poa.core.ui.di

import com.spoonofcode.poa.core.ui.utils.FirebasePushService
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val platformUiModule = module {
    factoryOf(::FirebasePushService)
}