package com.spoonofcode.feature.product.data.test.di

import com.spoonofcode.feature.product.data.test.productRepositoryMock
import org.koin.dsl.module

val productDataTestModule =
    module {
        includes(
            module {
                single { productRepositoryMock() }
            },
        )
    }
