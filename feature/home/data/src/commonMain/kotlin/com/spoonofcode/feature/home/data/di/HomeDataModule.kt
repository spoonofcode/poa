package com.spoonofcode.feature.home.data.di

import com.spoonofcode.feature.home.data.repository.RewardRepositoryImpl
import com.spoonofcode.feature.home.data.remote.RemoteUserRewardDataSource
import com.spoonofcode.feature.home.domain.repository.RewardRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeDataModule = module {
    singleOf(::RemoteUserRewardDataSource)

    singleOf(::RewardRepositoryImpl).bind<RewardRepository>()
}