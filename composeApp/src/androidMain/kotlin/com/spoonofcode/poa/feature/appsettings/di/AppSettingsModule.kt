package com.spoonofcode.poa.feature.appsettings.di

import com.spoonofcode.poa.feature.appsettings.AppSettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val appSettingsModule = module {
    viewModelOf(::AppSettingsViewModel)
}