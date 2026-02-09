package com.spoonofcode.feature.profile.presentation.di

import com.spoonofcode.core.test.di.coreTestModule
import org.koin.dsl.module

val profileTestModule = module {
    includes(
//        settingsModule,
//        networkTestModule,
//        dataTestModule,
//        domainModule,
//        profileModule,
//        uiModule,
        coreTestModule,
        profilePresentationModule,
    )
}