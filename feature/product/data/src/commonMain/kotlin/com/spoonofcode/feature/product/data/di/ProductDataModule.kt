package com.spoonofcode.feature.product.data.di

import org.koin.dsl.module

val productDataModule = module {
    singleOf(::RemoteProductDataSource)
    singleOf(::RemoteUserProductDataSource)

    singleOf(::ProductRepositoryImpl).bind<ProductRepository>()
}