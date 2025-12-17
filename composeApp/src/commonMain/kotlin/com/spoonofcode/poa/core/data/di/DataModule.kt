package com.spoonofcode.poa.core.data.di

import com.spoonofcode.poa.core.data.repository.NotificationRemoteDataSource
import com.spoonofcode.poa.core.data.repository.NotificationRepository
import com.spoonofcode.poa.core.data.repository.PartnerCategoryRemoteDataSource
import com.spoonofcode.poa.core.data.repository.PartnerCategoryRepository
import com.spoonofcode.poa.core.data.repository.PartnerRemoteDataSource
import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.data.repository.ProductRemoteDataSource
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.data.repository.RefreshRepository
import com.spoonofcode.poa.core.data.repository.RefreshRepositoryImpl
import com.spoonofcode.poa.core.data.repository.UserRemoteDataSource
import com.spoonofcode.poa.core.data.repository.UserRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::RefreshRepositoryImpl) { bind<RefreshRepository>() }
    singleOf(::UserRemoteDataSource) { bind<UserRepository>() }

    singleOf(::ProductRemoteDataSource) { bind<ProductRepository>() }
    singleOf(::PartnerRemoteDataSource) { bind<PartnerRepository>() }
    singleOf(::PartnerCategoryRemoteDataSource) { bind<PartnerCategoryRepository>() }
    singleOf(::NotificationRemoteDataSource) { bind<NotificationRepository>() }
}