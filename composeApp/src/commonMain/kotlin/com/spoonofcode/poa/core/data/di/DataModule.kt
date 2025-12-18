package com.spoonofcode.poa.core.data.di

import com.spoonofcode.poa.feature.notification.domain.RemoteNotificationDataSource
import com.spoonofcode.poa.feature.notification.domain.NotificationDataSource
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

    singleOf(::RemoteNotificationDataSource) { bind<NotificationDataSource>() }
}