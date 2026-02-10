package com.spoonofcode.feature.product.presentation.di

import com.spoonofcode.feature.product.domain.di.productDomainModule
import com.spoonofcode.feature.product.presentation.productRepositoryMock
import org.koin.dsl.module

val productPresentationTestModule = module {
    includes(
        module {
            single { productRepositoryMock() }
        },
        productDomainModule,
        productPresentationModule,
    )
}