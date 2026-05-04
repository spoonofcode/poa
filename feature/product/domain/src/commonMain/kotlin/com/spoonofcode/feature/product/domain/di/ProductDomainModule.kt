package com.spoonofcode.feature.product.domain.di

import com.spoonofcode.feature.notification.domain.usecase.NotificationTopicSyncHandler
import com.spoonofcode.feature.product.domain.usecase.AddProductToUserUseCase
import com.spoonofcode.feature.product.domain.usecase.DeleteProductFromUserUseCase
import com.spoonofcode.feature.product.domain.usecase.GetFilteredProductsByTextUseCase
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.feature.product.domain.usecase.GetProductStatusUseCase
import com.spoonofcode.feature.product.domain.usecase.GetUserProductsUseCase
import com.spoonofcode.feature.product.domain.usecase.ProductNotificationTopicSyncHandler
import com.spoonofcode.feature.product.domain.usecase.UpdateProductUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val productDomainModule = module {
    factoryOf(::GetFilteredProductsByTextUseCase)
    factoryOf(::GetProductByIdUseCase)
    factoryOf(::UpdateProductUseCase)
    factoryOf(::GetUserProductsUseCase)
    factoryOf(::AddProductToUserUseCase)
    factoryOf(::DeleteProductFromUserUseCase)
    factoryOf(::GetProductStatusUseCase)
    factoryOf(::ProductNotificationTopicSyncHandler) { bind<NotificationTopicSyncHandler>() }
}