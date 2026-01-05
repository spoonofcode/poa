package com.spoonofcode.poa.feature.product.di

import com.spoonofcode.poa.feature.product.data.ProductRepositoryImpl
import com.spoonofcode.poa.feature.product.data.remote.RemoteProductDataSource
import com.spoonofcode.poa.feature.product.data.remote.RemoteUserProductDataSource
import com.spoonofcode.poa.feature.product.domain.repository.ProductRepository
import com.spoonofcode.poa.feature.product.domain.usecase.AddProductToUserUseCase
import com.spoonofcode.poa.feature.product.domain.usecase.DeleteProductFromUserUseCase
import com.spoonofcode.poa.feature.product.domain.usecase.GetFilteredProductsByTextUseCase
import com.spoonofcode.poa.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.poa.feature.product.domain.usecase.GetProductByTagIdUseCase
import com.spoonofcode.poa.feature.product.domain.usecase.GetProductSeriesUseCase
import com.spoonofcode.poa.feature.product.domain.usecase.GetUserProductsUseCase
import com.spoonofcode.poa.feature.product.domain.usecase.UpdateProductUseCase
import com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewModel
import com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewModel
import com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewModel
import com.spoonofcode.poa.feature.product.presentation.series.overview.ProductSeriesOverviewViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val productModule = module {
    singleOf(::RemoteProductDataSource)
    singleOf(::RemoteUserProductDataSource)

    singleOf(::ProductRepositoryImpl).bind<ProductRepository>()

    factoryOf(::GetFilteredProductsByTextUseCase)
    factoryOf(::GetProductByIdUseCase)
    factoryOf(::GetProductByTagIdUseCase)
    factoryOf(::GetProductSeriesUseCase)
    factoryOf(::UpdateProductUseCase)
    factoryOf(::GetUserProductsUseCase)
    factoryOf(::AddProductToUserUseCase)
    factoryOf(::DeleteProductFromUserUseCase)

    viewModelOf(::ProductDetailsViewModel)
    viewModelOf(::ProductEditViewModel)
    viewModelOf(::ProductOverviewViewModel)
    viewModelOf(::ProductSeriesOverviewViewModel)
}