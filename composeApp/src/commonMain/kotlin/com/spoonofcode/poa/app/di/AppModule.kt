package com.spoonofcode.poa.app.di

import com.spoonofcode.core.data.coroutines.di.coroutinesModule
import com.spoonofcode.core.data.getPlatform
import com.spoonofcode.core.logging.di.loggingModule
import com.spoonofcode.core.firebase.data.di.firebaseDataModule
import com.spoonofcode.core.network.di.networkModule
import com.spoonofcode.core.presentation.di.presentationModule
import com.spoonofcode.core.recaptcha.di.recaptchaModule
import com.spoonofcode.core.session.data.di.sessionDataModule
import com.spoonofcode.core.session.domain.di.sessionDomainModule
import com.spoonofcode.core.storage.data.di.storageDataModule
import com.spoonofcode.feature.auth.data.di.authDataModule
import com.spoonofcode.feature.auth.domain.di.authDomainModule
import com.spoonofcode.feature.auth.presentation.di.authPresentationModule
import com.spoonofcode.feature.faq.presentation.di.faqPresentationModule
import com.spoonofcode.feature.home.data.di.homeDataModule
import com.spoonofcode.feature.home.domain.di.homeDomainModule
import com.spoonofcode.feature.home.presentation.di.homePresentationModule
import com.spoonofcode.feature.notification.data.di.notificationDataModule
import com.spoonofcode.feature.notification.domain.di.notificationDomainModule
import com.spoonofcode.feature.notification.presentation.di.notificationPresentationModule
import com.spoonofcode.feature.partner.data.di.partnerDataModule
import com.spoonofcode.feature.partner.domain.di.partnerDomainModule
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationModule
import com.spoonofcode.feature.product.data.di.productDataModule
import com.spoonofcode.feature.product.domain.di.productDomainModule
import com.spoonofcode.feature.product.presentation.di.productPresentationModule
import com.spoonofcode.feature.profile.data.di.profileDataModule
import com.spoonofcode.feature.profile.domain.di.profileDomainModule
import com.spoonofcode.feature.profile.presentation.di.profilePresentationModule
import com.spoonofcode.feature.scan.nfc.data.di.scanDataModule
import com.spoonofcode.feature.scan.nfc.domain.di.scanDomainModule
import com.spoonofcode.feature.scan.nfc.presentation.di.scanPresentationModule
import com.spoonofcode.feature.tac.data.di.tacDataModule
import com.spoonofcode.feature.tac.domain.di.tacDomainModule
import com.spoonofcode.feature.tac.presentation.di.tacPresentationModule
import com.spoonofcode.poa.app.AppViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(
        // core
        loggingModule,
        coroutinesModule,
        firebaseDataModule,
        presentationModule,
        networkModule,
        recaptchaModule,
        sessionDataModule,
        sessionDomainModule,
        storageDataModule,

        // features
        homeDataModule,
        homeDomainModule,
        homePresentationModule,

        authDataModule,
        authDomainModule,
        authPresentationModule,

        faqPresentationModule,

        partnerDataModule,
        partnerDomainModule,
        partnerPresentationModule,

        profileDataModule,
        profileDomainModule,
        profilePresentationModule,

        productDataModule,
        productDomainModule,
        productPresentationModule,

        notificationDataModule,
        notificationDomainModule,
        notificationPresentationModule,

        scanDataModule,
        scanDomainModule,
        scanPresentationModule,

        tacDataModule,
        tacDomainModule,
        tacPresentationModule,
    )
    single { getPlatform() }
    viewModelOf(::AppViewModel)
}