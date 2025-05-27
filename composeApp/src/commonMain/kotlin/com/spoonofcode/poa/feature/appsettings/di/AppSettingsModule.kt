package com.spoonofcode.poa.feature.appsettings.di

import com.spoonofcode.poa.feature.appsettings.AppSettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appSettingsModule: Module = module {
    viewModelOf(::AppSettingsViewModel)
}