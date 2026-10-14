package com.spoonofcode.feature.product.domain.di

import com.spoonofcode.core.firebase.data.test.di.firebaseDataTestModule
import com.spoonofcode.core.logging.di.loggingModule
import com.spoonofcode.feature.notification.data.test.di.notificationDataTestModule
import com.spoonofcode.feature.notification.domain.di.notificationDomainModule
import com.spoonofcode.feature.product.data.test.di.productDataTestModule
import org.koin.dsl.module

val productDomainTestModule = module {
    includes(
        loggingModule,
        firebaseDataTestModule,
        notificationDataTestModule,
        notificationDomainModule,
        productDataTestModule,
        productDomainModule,
    )
}
