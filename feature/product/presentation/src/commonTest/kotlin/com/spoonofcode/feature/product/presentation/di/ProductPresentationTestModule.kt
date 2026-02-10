package com.spoonofcode.feature.product.presentation.di

import com.spoonofcode.feature.product.data.test.di.productDataTestModule
import com.spoonofcode.feature.product.domain.di.productDomainModule
import org.koin.dsl.module

val productPresentationTestModule = module {
    includes(
        productDataTestModule,
        productDomainModule,
        productPresentationModule,
    )
}