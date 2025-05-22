package com.spoonofcode.poa.feature.profile.di

import com.spoonofcode.poa.core.domain.di.domainModule
import com.spoonofcode.poa.core.network.di.networkModule
import com.spoonofcode.poa.core.settings.di.settingsModule
import com.spoonofcode.poa.core.ui.di.uiModule
import org.koin.dsl.module

val profileTestModule = module {
    includes(
        settingsModule,
        networkModule,
//        dataModule,
//        dataTestModule,
        domainModule,
        profileModule,
        uiModule,
    )
}