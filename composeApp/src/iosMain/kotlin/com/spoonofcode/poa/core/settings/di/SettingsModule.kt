package com.spoonofcode.poa.core.settings.di

import com.spoonofcode.poa.core.settings.createEncryptedSettings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val settingsModule = module {
    singleOf(::createEncryptedSettings)
}