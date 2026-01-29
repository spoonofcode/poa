package com.spoonofcode.poa.app.di

import com.spoonofcode.core.data.coroutines.di.coroutinesModule
import com.spoonofcode.core.data.domain.di.domainModule
import com.spoonofcode.core.data.network.di.networkModule
import com.spoonofcode.core.data.recaptcha.di.recaptchaModule
import com.spoonofcode.core.data.settings.di.settingsModule
import com.spoonofcode.core.presentation.di.uiModule
import com.spoonofcode.feature.auth.presentation.di.loginModule
import com.spoonofcode.feature.home.presentation.di.homeModule
import com.spoonofcode.feature.home.presentation.di.rewardModule
import com.spoonofcode.feature.notification.presentation.di.notificationModule
import com.spoonofcode.feature.partner.data.di.partnerDataModule
import com.spoonofcode.feature.partner.domain.di.partnerDomainModule
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationModule
import com.spoonofcode.feature.product.data.di.productDataModule
import com.spoonofcode.feature.product.domain.di.productDomainModule
import com.spoonofcode.feature.product.presentation.di.productPresentationModule
import com.spoonofcode.feature.profile.data.di.profileDataModule
import com.spoonofcode.feature.profile.domain.di.profileDomainModule
import com.spoonofcode.feature.profile.presentation.di.profilePresentationModule
import com.spoonofcode.feature.scan.presentation.di.scanPresentationModule
import com.spoonofcode.feature.tac.presentation.di.tacModule
import com.spoonofcode.poa.app.getPlatform
import org.koin.dsl.module

val appModule = module {
    includes(
        // core
        coroutinesModule,
        domainModule,
        uiModule,
        networkModule,
        recaptchaModule,
        settingsModule,

        // features
        homeModule,
        loginModule,

        partnerDataModule,
        partnerDomainModule,
        partnerPresentationModule,

        profileDataModule,
        profileDomainModule,
        profilePresentationModule,

        productDataModule,
        productDomainModule,
        productPresentationModule,
        rewardModule,
        notificationModule,
        scanPresentationModule,
        tacModule,
    )

    single { getPlatform() }
}