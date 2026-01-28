package com.spoonofcode.core.presentation.di

import com.spoonofcode.core.presentation.utils.FirebasePushService
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val platformUiModule = module {
    factoryOf(::FirebasePushService)
}