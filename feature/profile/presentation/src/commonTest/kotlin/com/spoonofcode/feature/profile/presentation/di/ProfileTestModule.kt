package com.spoonofcode.feature.profile.presentation.di

import com.spoonofcode.core.data.settings.di.settingsModule
import com.spoonofcode.core.test.di.coreTestModule
import com.spoonofcode.feature.profile.domain.di.profileDomainModule
import com.spoonofcode.feature.profile.presentation.profileRepositoryMock
import org.koin.dsl.module

val profileTestModule = module {
    includes(
//        settingsModule,
//        networkTestModule,
//        dataTestModule,
//        domainModule,
//        uiModule,
//        settingsModule,
        module {
            single { profileRepositoryMock() }
        },
        coreTestModule,
        profileDomainModule,
        profilePresentationModule,
    )
}