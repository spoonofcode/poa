package com.spoonofcode.poa.feature.home.di

import com.spoonofcode.poa.core.data.di.dataTestModule
import com.spoonofcode.poa.core.domain.di.domainModule
import com.spoonofcode.poa.core.network.di.networkTestModule
import com.spoonofcode.poa.core.settings.di.settingsModule
import com.spoonofcode.core.presentation.di.uiModule
import com.spoonofcode.poa.feature.reward.di.rewardModule
import org.koin.dsl.module

val homeTestModule = module {
    includes(
        settingsModule,
        networkTestModule,
        dataTestModule,
        domainModule,
        homeModule,
        uiModule,
        rewardModule,
    )
}