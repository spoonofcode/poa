package com.spoonofcode.core.data.settings.di

import com.russhwolf.settings.Settings
import com.spoonofcode.core.data.settings.IosSettingsFactory
import org.koin.dsl.module

actual val platformSettingsModule = module {
    single<Settings.Factory> { IosSettingsFactory() }
}
