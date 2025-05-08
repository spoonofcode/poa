package com.spoonofcode.poa.feature.appsettings.di

import com.spoonofcode.poa.feature.appsettings.AppSettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val appSettingsModule = module {
    singleOf(::AppSettingsViewModel)
}