package com.spoonofcode.feature.partner.data.di

import com.spoonofcode.feature.partner.data.remote.RemotePartnerCategoryDataSource
import com.spoonofcode.feature.partner.data.remote.RemotePartnerDataSource
import com.spoonofcode.feature.partner.data.remote.RemotePartnerNotificationDataSource
import com.spoonofcode.feature.partner.data.repository.PartnerNotificationRepositoryImpl
import com.spoonofcode.feature.partner.data.repository.PartnerRepositoryImpl
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val partnerDataModule =
    module {
        singleOf(::RemotePartnerDataSource)
        singleOf(::RemotePartnerCategoryDataSource)
        singleOf(::RemotePartnerNotificationDataSource)

        singleOf(::PartnerRepositoryImpl).bind<PartnerRepository>()
        singleOf(::PartnerNotificationRepositoryImpl).bind<PartnerNotificationRepository>()
    }
