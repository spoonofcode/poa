package com.spoonofcode.core.test.di

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import com.spoonofcode.core.test.network.sessionRepositoryMock
import org.koin.dsl.module

// TODO #131 Fix test
internal val testModule = module {
    single<Settings> { get<Settings.Factory>().create("poa-settings") }
    single<Settings> { MapSettings() }
    single { sessionRepositoryMock() }
}