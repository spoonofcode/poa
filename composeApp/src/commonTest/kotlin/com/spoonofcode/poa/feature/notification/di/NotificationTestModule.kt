package com.spoonofcode.poa.feature.notification.di

import com.spoonofcode.poa.core.data.di.dataTestModule
import com.spoonofcode.poa.core.domain.di.domainModule
import com.spoonofcode.poa.core.network.di.networkTestModule
import com.spoonofcode.poa.core.settings.di.settingsModule
import com.spoonofcode.core.presentation.di.uiModule
import com.spoonofcode.feature.partner.di.partnerModule
import org.koin.dsl.module

val notificationTestModule = module {
    includes(
        settingsModule,
        networkTestModule,
        dataTestModule,
        domainModule,
        notificationModule,
        partnerModule,
        uiModule,
    )
}