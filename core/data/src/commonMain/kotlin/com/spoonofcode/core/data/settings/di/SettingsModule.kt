package com.spoonofcode.core.data.settings.di

import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformSettingsModule: Module

val settingsModule: Module = module {
    includes(platformSettingsModule)
    single<Settings> { get<Settings.Factory>().create("poa-settings") }
}
