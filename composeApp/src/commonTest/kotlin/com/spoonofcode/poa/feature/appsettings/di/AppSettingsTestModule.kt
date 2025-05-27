package com.spoonofcode.poa.feature.appsettings.di

import com.spoonofcode.poa.core.data.di.dataTestModule
import com.spoonofcode.poa.core.domain.di.domainModule
import com.spoonofcode.poa.core.network.di.networkTestModule
import com.spoonofcode.poa.core.settings.di.settingsModule
import com.spoonofcode.poa.core.ui.di.uiModule
import org.koin.dsl.module

val appSettingsTestModule = module {
    includes(
        settingsModule,
        networkTestModule,
        dataTestModule,
        domainModule,
        appSettingsModule,
        uiModule,
    )
}