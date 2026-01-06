package com.spoonofcode.poa.feature.reward.di

import com.spoonofcode.poa.feature.reward.data.RewardRepositoryImpl
import com.spoonofcode.poa.feature.reward.data.remote.RemoteUserRewardDataSource
import com.spoonofcode.poa.feature.reward.domain.repository.RewardRepository
import com.spoonofcode.poa.feature.reward.domain.usecase.AddRewardToUserUseCase
import com.spoonofcode.poa.feature.reward.domain.usecase.GetUserDailyCheckInStatusUseCase
import com.spoonofcode.poa.feature.reward.domain.usecase.GetUserRewardsPointsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val rewardModule = module {
    singleOf(::RemoteUserRewardDataSource)

    singleOf(::RewardRepositoryImpl).bind<RewardRepository>()

    factoryOf(::AddRewardToUserUseCase)
    factoryOf(::GetUserDailyCheckInStatusUseCase)
    factoryOf(::GetUserRewardsPointsUseCase)
}