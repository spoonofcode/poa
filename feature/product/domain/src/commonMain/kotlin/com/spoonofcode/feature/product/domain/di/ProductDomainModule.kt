package com.spoonofcode.feature.product.domain.di

import com.spoonofcode.feature.product.domain.usecase.AddProductToUserUseCase
import com.spoonofcode.feature.product.domain.usecase.DeleteProductFromUserUseCase
import com.spoonofcode.feature.product.domain.usecase.GetFilteredProductsByTextUseCase
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.feature.product.domain.usecase.GetProductSeriesUseCase
import com.spoonofcode.feature.product.domain.usecase.GetUserProductsUseCase
import com.spoonofcode.feature.product.domain.usecase.UpdateProductUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val productDomainModule = module {
    factoryOf(::GetFilteredProductsByTextUseCase)
    factoryOf(::GetProductByIdUseCase)
    factoryOf(::GetProductSeriesUseCase)
    factoryOf(::UpdateProductUseCase)
    factoryOf(::GetUserProductsUseCase)
    factoryOf(::AddProductToUserUseCase)
    factoryOf(::DeleteProductFromUserUseCase)
}