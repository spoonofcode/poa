package com.spoonofcode.core.data.settings.di

import com.russhwolf.settings.Settings
import com.spoonofcode.core.data.settings.AndroidSettingsFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformSettingsModule = module {
    single<Settings.Factory> { AndroidSettingsFactory(androidContext()) }
}