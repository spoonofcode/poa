package com.spoonofcode.poa.app.di

import com.spoonofcode.poa.core.coroutines.di.coroutinesModule
import com.spoonofcode.poa.core.domain.di.domainModule
import com.spoonofcode.poa.core.network.di.networkModule
import com.spoonofcode.poa.core.recaptcha.di.recaptchaModule
import com.spoonofcode.poa.core.settings.di.settingsModule
import com.spoonofcode.poa.core.ui.di.uiModule
import com.spoonofcode.poa.feature.demo.di.demoModule
import com.spoonofcode.poa.feature.faq.di.faqModule
import com.spoonofcode.poa.feature.home.di.homeModule
import com.spoonofcode.poa.feature.login.di.loginModule
import com.spoonofcode.poa.feature.notification.di.notificationModule
import com.spoonofcode.poa.feature.partner.di.partnerModule
import com.spoonofcode.poa.feature.product.di.productModule
import com.spoonofcode.poa.feature.profile.di.profileModule
import com.spoonofcode.poa.feature.reward.di.rewardModule
import com.spoonofcode.poa.feature.scan.di.scanModule
import com.spoonofcode.poa.feature.tac.di.tacModule
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
        demoModule,
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
}