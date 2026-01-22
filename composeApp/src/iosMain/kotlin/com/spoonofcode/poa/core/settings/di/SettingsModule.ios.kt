package com.spoonofcode.poa.core.settings.di

import com.russhwolf.settings.Settings
import com.spoonofcode.poa.core.settings.IosSettingsFactory
import org.koin.dsl.module

actual val platformSettingsModule = module {
    single<Settings.Factory> { IosSettingsFactory() }
}
