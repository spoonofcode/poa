package com.spoonofcode.poa.core.data.di

import com.spoonofcode.poa.feature.faq.domain.FAQDataSource
import com.spoonofcode.poa.feature.faq.data.remote.RemoteFAQDataSource
import com.spoonofcode.poa.core.data.repository.LoginGoogleRepository
import com.spoonofcode.poa.core.data.repository.LoginGoogleRemoteDataSource
import com.spoonofcode.poa.core.data.repository.LoginRepository
import com.spoonofcode.poa.core.data.repository.LoginRemoteDataSource
import com.spoonofcode.poa.core.data.repository.NotificationRepository
import com.spoonofcode.poa.core.data.repository.NotificationRemoteDataSource
import com.spoonofcode.poa.core.data.repository.PartnerCategoryRepository
import com.spoonofcode.poa.core.data.repository.PartnerCategoryRemoteDataSource
import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.data.repository.PartnerRemoteDataSource
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.data.repository.ProductRemoteDataSource
import com.spoonofcode.poa.core.data.repository.RefreshRepository
import com.spoonofcode.poa.core.data.repository.RefreshRepositoryImpl
import com.spoonofcode.poa.core.data.repository.RegisterRepository
import com.spoonofcode.poa.core.data.repository.RegisterRemoteDataSource
import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.data.repository.UserRemoteDataSource
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::RemoteFAQDataSource) { bind<FAQDataSource>() }
    singleOf(::LoginGoogleRemoteDataSource) { bind<LoginGoogleRepository>() }
    singleOf(::LoginRemoteDataSource) { bind<LoginRepository>() }
    singleOf(::RefreshRepositoryImpl) { bind<RefreshRepository>() }
    singleOf(::UserRemoteDataSource) { bind<UserRepository>() }
    singleOf(::RegisterRemoteDataSource) { bind<RegisterRepository>() }
    singleOf(::ProductRemoteDataSource) { bind<ProductRepository>() }
    singleOf(::PartnerRemoteDataSource) { bind<PartnerRepository>() }
    singleOf(::PartnerCategoryRemoteDataSource) { bind<PartnerCategoryRepository>() }
    singleOf(::NotificationRemoteDataSource) { bind<NotificationRepository>() }
}