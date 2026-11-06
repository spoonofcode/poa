package com.spoonofcode.feature.product.data.di

import com.spoonofcode.feature.product.data.remote.RemoteProductDataSource
import com.spoonofcode.feature.product.data.repository.ProductRepositoryImpl
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val productDataModule =
    module {
        singleOf(::RemoteProductDataSource)

        singleOf(::ProductRepositoryImpl).bind<ProductRepository>()
    }
