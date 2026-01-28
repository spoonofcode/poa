package com.spoonofcode.poa.feature.scan.di

import com.spoonofcode.poa.core.data.di.dataTestModule
import com.spoonofcode.poa.core.domain.di.domainModule
import com.spoonofcode.poa.core.network.di.networkTestModule
import com.spoonofcode.poa.core.settings.di.settingsModule
import com.spoonofcode.core.presentation.di.uiModule
import com.spoonofcode.poa.feature.product.di.productModule
import org.koin.dsl.module

val scanTestModule = module {
    includes(
        settingsModule,
        networkTestModule,
        dataTestModule,
        domainModule,
        scanModule,
        uiModule,
        productModule,
    )
}