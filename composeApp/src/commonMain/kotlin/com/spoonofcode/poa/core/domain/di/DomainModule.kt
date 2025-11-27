package com.spoonofcode.poa.core.domain.di

import com.spoonofcode.poa.core.domain.DeleteAccountUseCase
import com.spoonofcode.poa.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.domain.home.ClaimTodayRewardUseCase
import com.spoonofcode.poa.core.domain.home.GetDailyRewardsUseCase
import com.spoonofcode.poa.core.domain.login.LoginGoogleUseCase
import com.spoonofcode.poa.core.domain.login.LoginUseCase
import com.spoonofcode.poa.core.domain.login.LogoutUseCase
import com.spoonofcode.poa.core.domain.login.RegisterUseCase
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
import com.spoonofcode.poa.core.domain.product.DeleteProductUseCase
import com.spoonofcode.poa.core.domain.product.EditProductUseCase
import com.spoonofcode.poa.core.domain.product.GetFilteredProductsByTextUseCase
import com.spoonofcode.poa.core.domain.product.GetProductByIdUseCase
import com.spoonofcode.poa.core.domain.product.GetProductByTagIdUseCase
import com.spoonofcode.poa.core.domain.product.GetProductSeriesUseCase
import com.spoonofcode.poa.core.domain.profile.GetProfileUseCase
import com.spoonofcode.poa.core.domain.user.product.AddProductToUserUseCase
import com.spoonofcode.poa.core.domain.user.GetUserByIdUseCase
import com.spoonofcode.poa.core.domain.user.partner.GetUserPartnersUseCase
import com.spoonofcode.poa.core.domain.user.reward.GetUserRewardsPointsUseCase
import com.spoonofcode.poa.core.domain.user.product.GetUserProductsUseCase
import com.spoonofcode.poa.core.domain.user.SubscribeUserTopicsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::DeleteAccountUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::LoginGoogleUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::RefreshAccessTokenUseCase)
    factoryOf(::GetProfileUseCase)
    factoryOf(::GetUserByIdUseCase)
    factoryOf(::GetFilteredProductsByTextUseCase)
    factoryOf(::GetPartnerByIdUseCase)
    factoryOf(::GetPartnerCategoriesUseCase)
    factoryOf(::GetPartnersFilteredByTextUseCase)
    factoryOf(::GetProductByIdUseCase)
    factoryOf(::GetProductByTagIdUseCase)
    factoryOf(::GetUserProductsUseCase)
    factoryOf(::AddProductToUserUseCase)
    factoryOf(::DeleteProductUseCase)
    factoryOf(::EditProductUseCase)
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
    factoryOf(::GetDailyRewardsUseCase)
    factoryOf(::ClaimTodayRewardUseCase)
    factoryOf(::GetUserRewardsPointsUseCase)
    factoryOf(::GetUserPartnersUseCase)
}