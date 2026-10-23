package com.spoonofcode.feature.product.presentation.di

import com.spoonofcode.core.firebase.data.test.di.firebaseDataTestModule
import com.spoonofcode.core.logging.di.loggingModule
import com.spoonofcode.feature.notification.data.test.di.notificationDataTestModule
import com.spoonofcode.feature.partner.data.test.di.partnerDataTestModule
import com.spoonofcode.feature.partner.domain.di.partnerDomainModule
import com.spoonofcode.feature.product.data.test.di.productDataTestModule
import com.spoonofcode.feature.product.domain.di.productDomainModule
import org.koin.dsl.module

val productPresentationTestModule =
    module {
        includes(
            loggingModule,
            productDataTestModule,
            productDomainModule,
            productPresentationModule,
            notificationDataTestModule,
            firebaseDataTestModule,
            partnerDataTestModule,
            partnerDomainModule,
        )
    }
