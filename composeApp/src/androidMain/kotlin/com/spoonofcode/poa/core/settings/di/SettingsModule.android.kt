package com.spoonofcode.poa.core.settings.di

import com.spoonofcode.poa.core.settings.createEncryptedSettings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformSettingsModule = module {
    singleOf(::createEncryptedSettings)
}