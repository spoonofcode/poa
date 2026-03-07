package com.spoonofcode.feature.home.presentation.di

//import com.spoonofcode.core.firebase.data.di.firebaseDataModule
//import com.spoonofcode.core.firebase.domain.di.firebaseDomainModule
import com.spoonofcode.feature.home.data.test.di.homeDataTestModule
import com.spoonofcode.feature.home.domain.di.homeDomainModule
import com.spoonofcode.feature.notification.data.test.di.notificationDataTestModule
import com.spoonofcode.feature.notification.domain.di.notificationDomainModule
import com.spoonofcode.feature.partner.data.test.di.partnerDataTestModule
import com.spoonofcode.feature.partner.domain.di.partnerDomainModule
import org.koin.dsl.module

val homePresentationTestModule = module {
    includes(
        homeDataTestModule,
        homeDomainModule,
        homePresentationModule,

//        firebaseDataModule,
//        firebaseDomainModule,

        notificationDomainModule,
        notificationDataTestModule,

        partnerDomainModule,
        partnerDataTestModule,
    )
}