package com.spoonofcode.feature.product.presentation.di

import com.spoonofcode.core.test.di.coreTestModule
import com.spoonofcode.feature.product.domain.di.productDomainModule
import org.koin.dsl.module

val productPresentationTestModule = module {
    includes(
        coreTestModule,
        productDomainModule,
        productPresentationModule,
    )
}