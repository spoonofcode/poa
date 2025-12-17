package com.spoonofcode.poa.core.domain.di

import com.spoonofcode.poa.core.domain.DeleteAccountUseCase
import com.spoonofcode.poa.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.domain.notification.GetNotificationByIdUseCase
import com.spoonofcode.poa.core.domain.notification.GetNotificationSettingsUseCase
import com.spoonofcode.poa.core.domain.notification.GetNotificationsUseCase
import com.spoonofcode.poa.core.domain.notification.SendNotificationUseCase
import com.spoonofcode.poa.core.domain.notification.UpdateNotificationSettingsUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnerByIdUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnerCategoriesUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnerUsersUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnersFilteredByTextUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnersUseCase
import com.spoonofcode.poa.core.domain.partner.UpdatePartnerUseCase
import com.spoonofcode.poa.core.domain.product.GetFilteredProductsByTextUseCase
import com.spoonofcode.poa.core.domain.product.GetProductByIdUseCase
import com.spoonofcode.poa.core.domain.product.GetProductByTagIdUseCase
import com.spoonofcode.poa.core.domain.product.GetProductSeriesUseCase
import com.spoonofcode.poa.core.domain.product.UpdateProductUseCase
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
    factoryOf(::GetFilteredProductsByTextUseCase)
    factoryOf(::GetPartnerByIdUseCase)
    factoryOf(::GetPartnerCategoriesUseCase)
    factoryOf(::GetPartnersFilteredByTextUseCase)
    factoryOf(::GetProductByIdUseCase)
    factoryOf(::GetProductByTagIdUseCase)
    factoryOf(::GetUserProductsUseCase)
    factoryOf(::AddProductToUserUseCase)
    factoryOf(::DeleteProductFromUserUseCase)
    factoryOf(::UpdateProductUseCase)
    factoryOf(::UpdatePartnerUseCase)
    factoryOf(::GetProductSeriesUseCase)
    factoryOf(::SendNotificationUseCase)
    factoryOf(::GetNotificationsUseCase)
    factoryOf(::GetNotificationByIdUseCase)
    factoryOf(::SubscribeUserTopicsUseCase)
    factoryOf(::GetPartnersUseCase)
    factoryOf(::GetPartnerUsersUseCase)
    factoryOf(::GetNotificationSettingsUseCase)
    factoryOf(::UpdateNotificationSettingsUseCase)
    factoryOf(::GetUserRewardsPointsUseCase)
    factoryOf(::GetUserPartnersUseCase)
    factoryOf(::GetUserDailyCheckInStatusUseCase)
    factoryOf(::AddRewardToUserUseCase)
}