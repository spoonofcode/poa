package com.spoonofcode.feature.partner.presentation.di

import com.spoonofcode.feature.partner.data.test.di.partnerDataTestModule
import com.spoonofcode.feature.partner.domain.di.partnerDomainModule
import org.koin.dsl.module

val partnerPresentationTestModule = module {
    includes(
        partnerDataTestModule,
        partnerDomainModule,
        partnerPresentationModule,
    )
}