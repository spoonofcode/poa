package com.spoonofcode.feature.home.presentation.di

import com.spoonofcode.feature.reward.data.RewardRepositoryImpl
import com.spoonofcode.feature.reward.data.remote.RemoteUserRewardDataSource
import com.spoonofcode.feature.reward.domain.repository.RewardRepository
import com.spoonofcode.feature.reward.domain.usecase.AddRewardToUserUseCase
import com.spoonofcode.feature.reward.domain.usecase.GetUserDailyCheckInStatusUseCase
import com.spoonofcode.feature.reward.domain.usecase.GetUserRewardsPointsUseCase
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