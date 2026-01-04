package com.spoonofcode.poa.feature.reward.di

import com.spoonofcode.poa.feature.partner.domain.usecase.GetUserPartnersUseCase
import com.spoonofcode.poa.feature.reward.domain.usecase.AddRewardToUserUseCase
import com.spoonofcode.poa.feature.reward.domain.usecase.GetUserDailyCheckInStatusUseCase
import com.spoonofcode.poa.feature.reward.domain.usecase.GetUserRewardsPointsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val rewardModule = module {
    factoryOf(::AddRewardToUserUseCase)
    factoryOf(::GetUserDailyCheckInStatusUseCase)
    factoryOf(::GetUserRewardsPointsUseCase)
}