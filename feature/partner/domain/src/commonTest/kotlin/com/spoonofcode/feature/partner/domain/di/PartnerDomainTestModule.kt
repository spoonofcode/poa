package com.spoonofcode.feature.partner.domain.di

import com.spoonofcode.feature.partner.data.test.di.partnerDataTestModule
import org.koin.dsl.module

val partnerDomainTestModule = module {
    includes(
        partnerDataTestModule,
        partnerDomainModule,
    )
}
