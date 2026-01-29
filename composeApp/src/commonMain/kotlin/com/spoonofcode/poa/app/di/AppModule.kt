package com.spoonofcode.poa.app.di

import com.spoonofcode.core.data.coroutines.di.coroutinesModule
import com.spoonofcode.core.data.domain.di.domainModule
import com.spoonofcode.core.data.network.di.networkModule
import com.spoonofcode.core.data.recaptcha.di.recaptchaModule
import com.spoonofcode.core.data.settings.di.settingsModule
import com.spoonofcode.core.presentation.di.uiModule
import com.spoonofcode.poa.app.getPlatform
import com.spoonofcode.feature.faq.di.faqModule
import com.spoonofcode.feature.home.di.homeModule
import com.spoonofcode.feature.login.di.loginModule
import com.spoonofcode.feature.notification.di.notificationModule
import com.spoonofcode.feature.partner.di.partnerModule
import com.spoonofcode.feature.product.di.productModule
import com.spoonofcode.feature.profile.di.profileModule
import com.spoonofcode.feature.reward.di.rewardModule
import com.spoonofcode.feature.scan.di.scanModule
import com.spoonofcode.feature.tac.di.tacModule
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
        faqModule,
        homeModule,
        loginModule,
        partnerModule,
        profileModule,
        productModule,
        rewardModule,
        notificationModule,
        scanModule,
        tacModule,
    )

    single { getPlatform() }
}