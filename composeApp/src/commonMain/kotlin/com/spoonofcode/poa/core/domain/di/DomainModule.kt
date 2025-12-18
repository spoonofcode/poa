package com.spoonofcode.poa.core.domain.di

import com.spoonofcode.poa.core.domain.DeleteAccountUseCase
import com.spoonofcode.poa.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.domain.profile.GetProfileUseCase
import com.spoonofcode.poa.core.domain.profile.UpdateProfileUseCase
import com.spoonofcode.poa.core.domain.user.GetUserByIdUseCase
import com.spoonofcode.poa.core.domain.user.SubscribeUserTopicsUseCase
import com.spoonofcode.poa.core.domain.user.partner.GetUserPartnersUseCase
import com.spoonofcode.poa.core.domain.user.product.AddProductToUserUseCase
import com.spoonofcode.poa.core.domain.user.product.DeleteProductFromUserUseCase
import com.spoonofcode.poa.core.domain.user.product.GetUserProductsUseCase
import com.spoonofcode.poa.core.domain.user.reward.AddRewardToUserUseCase
import com.spoonofcode.poa.core.domain.user.reward.GetUserDailyCheckInStatusUseCase
import com.spoonofcode.poa.core.domain.user.reward.GetUserRewardsPointsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::DeleteAccountUseCase)
    factoryOf(::RefreshAccessTokenUseCase)
    factoryOf(::GetProfileUseCase)
    factoryOf(::UpdateProfileUseCase)
    factoryOf(::GetUserByIdUseCase)
    factoryOf(::GetUserProductsUseCase)
    factoryOf(::AddProductToUserUseCase)
    factoryOf(::DeleteProductFromUserUseCase)

    factoryOf(::SubscribeUserTopicsUseCase)

    factoryOf(::GetUserRewardsPointsUseCase)
    factoryOf(::GetUserPartnersUseCase)
    factoryOf(::GetUserDailyCheckInStatusUseCase)
    factoryOf(::AddRewardToUserUseCase)
}