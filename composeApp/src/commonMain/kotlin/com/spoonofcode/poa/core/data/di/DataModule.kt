package com.spoonofcode.poa.core.data.di

import com.spoonofcode.poa.core.data.repository.FAQRepository
import com.spoonofcode.poa.core.data.repository.FAQRepositoryImpl
import com.spoonofcode.poa.core.data.repository.LoginGoogleRepository
import com.spoonofcode.poa.core.data.repository.LoginGoogleRepositoryImpl
import com.spoonofcode.poa.core.data.repository.LoginRepository
import com.spoonofcode.poa.core.data.repository.LoginRepositoryImpl
import com.spoonofcode.poa.core.data.repository.NotificationRepository
import com.spoonofcode.poa.core.data.repository.NotificationRepositoryImpl
import com.spoonofcode.poa.core.data.repository.PartnerCategoryRepository
import com.spoonofcode.poa.core.data.repository.PartnerCategoryRepositoryImpl
import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.data.repository.PartnerRepositoryImpl
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.data.repository.ProductRepositoryImpl
import com.spoonofcode.poa.core.data.repository.RefreshRepository
import com.spoonofcode.poa.core.data.repository.RefreshRepositoryImpl
import com.spoonofcode.poa.core.data.repository.RegisterRepository
import com.spoonofcode.poa.core.data.repository.RegisterRepositoryImpl
import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.data.repository.UserRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::FAQRepositoryImpl) { bind<FAQRepository>() }
    singleOf(::LoginGoogleRepositoryImpl) { bind<LoginGoogleRepository>() }
    singleOf(::LoginRepositoryImpl) { bind<LoginRepository>() }
    singleOf(::RefreshRepositoryImpl) { bind<RefreshRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::RegisterRepositoryImpl) { bind<RegisterRepository>() }
    singleOf(::ProductRepositoryImpl) { bind<ProductRepository>() }
    singleOf(::PartnerRepositoryImpl) { bind<PartnerRepository>() }
    singleOf(::PartnerCategoryRepositoryImpl) { bind<PartnerCategoryRepository>() }
    singleOf(::NotificationRepositoryImpl) { bind<NotificationRepository>() }
}