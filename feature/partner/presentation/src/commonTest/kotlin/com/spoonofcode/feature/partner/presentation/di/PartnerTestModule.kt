package com.spoonofcode.feature.partner.presentation.di

import com.spoonofcode.feature.partner.domain.di.partnerDomainModule
import org.koin.dsl.module

val partnerPresentationTestModule = module {
    includes(
        partnerDomainModule,
        partnerPresentationModule,
    )
}