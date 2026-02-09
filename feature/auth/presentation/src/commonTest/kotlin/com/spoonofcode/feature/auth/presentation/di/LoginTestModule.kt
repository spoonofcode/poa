package com.spoonofcode.feature.auth.presentation.di

import com.spoonofcode.poa.core.data.di.dataTestModule
import com.spoonofcode.poa.core.domain.di.domainModule
import com.spoonofcode.poa.core.network.di.networkTestModule
import com.spoonofcode.poa.core.recaptcha.di.recaptchaTestModule
import com.spoonofcode.poa.core.settings.di.settingsModule
import com.spoonofcode.core.presentation.di.uiModule
import org.koin.dsl.module

val loginTestModule = module {
    includes(
        settingsModule,
        networkTestModule,
        recaptchaTestModule,
        dataTestModule,
        domainModule,
        loginModule,
        uiModule,
    )
}