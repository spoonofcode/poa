package com.spoonofcode.feature.home.presentation.di

import com.spoonofcode.core.firebase.data.test.di.firebaseDataTestModule
import com.spoonofcode.core.logging.di.loggingModule
import com.spoonofcode.feature.home.data.test.di.homeDataTestModule
import com.spoonofcode.feature.home.domain.di.homeDomainModule
import com.spoonofcode.feature.notification.data.test.di.notificationDataTestModule
import com.spoonofcode.feature.notification.domain.di.notificationDomainModule
import com.spoonofcode.feature.partner.data.test.di.partnerDataTestModule
import com.spoonofcode.feature.partner.domain.di.partnerDomainModule
import org.koin.dsl.module

val homePresentationTestModule = module {
    includes(
        loggingModule,
        homeDataTestModule,
        homeDomainModule,
        homePresentationModule,
        notificationDomainModule,
        notificationDataTestModule,
        partnerDomainModule,
        partnerDataTestModule,
        firebaseDataTestModule,
    )
}
