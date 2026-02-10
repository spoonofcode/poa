package com.spoonofcode.feature.home.domain.di

import com.spoonofcode.feature.home.domain.usecase.AddRewardToUserUseCase
import com.spoonofcode.feature.home.domain.usecase.GetUserDailyCheckInStatusUseCase
import com.spoonofcode.feature.home.domain.usecase.GetUserRewardsPointsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val homeDomainModule = module {
    factoryOf(::AddRewardToUserUseCase)
    factoryOf(::GetUserDailyCheckInStatusUseCase)
    factoryOf(::GetUserRewardsPointsUseCase)
}