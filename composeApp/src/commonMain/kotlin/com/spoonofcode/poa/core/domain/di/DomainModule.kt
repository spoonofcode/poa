package com.spoonofcode.poa.core.domain.di

import com.spoonofcode.poa.core.domain.DeleteAccountUseCase
import com.spoonofcode.poa.core.domain.HasSpecialSettingsUseCase
import com.spoonofcode.poa.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.domain.login.LoginGoogleUseCase
import com.spoonofcode.poa.core.domain.login.LoginUseCase
import com.spoonofcode.poa.core.domain.login.LogoutUseCase
import com.spoonofcode.poa.core.domain.login.RegisterUseCase
import com.spoonofcode.poa.core.domain.messagefcm.SendMessageFCMUseCase
import com.spoonofcode.poa.core.domain.notification.GetAllNotificationsUseCase
import com.spoonofcode.poa.core.domain.notification.GetNotificationByIdUseCase
import com.spoonofcode.poa.core.domain.notification.SendNotificationUseCase
import com.spoonofcode.poa.core.domain.partner.GetAllPartnerCategoriesUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnerByIdUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnersFilteredByCategoryUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnersFilteredByTextUseCase
import com.spoonofcode.poa.core.domain.product.GetAllProductSeriesIdsUseCase
import com.spoonofcode.poa.core.domain.product.GetFilteredProductsByTextUseCase
import com.spoonofcode.poa.core.domain.product.GetFilteredProductsUseCase
import com.spoonofcode.poa.core.domain.product.GetProductByIdUseCase
import com.spoonofcode.poa.core.domain.profile.GetProfileUseCase
import com.spoonofcode.poa.core.domain.role.AddRoleToUserUseCase
import com.spoonofcode.poa.core.domain.role.GetRolesByUserIdUseCase
import com.spoonofcode.poa.core.domain.user.GetAllUsersUseCase
import com.spoonofcode.poa.core.domain.user.GetUserByIdUseCase
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
    factoryOf(::GetRolesByUserIdUseCase)
    factoryOf(::GetAllUsersUseCase)
    factoryOf(::AddRoleToUserUseCase)
    factoryOf(::GetUserByIdUseCase)
    factoryOf(::HasSpecialSettingsUseCase)
    factoryOf(::SendMessageFCMUseCase)
    factoryOf(::GetFilteredProductsByTextUseCase)
    factoryOf(::GetPartnerByIdUseCase)
    factoryOf(::GetAllPartnerCategoriesUseCase)
    factoryOf(::GetPartnersFilteredByCategoryUseCase)
    factoryOf(::GetPartnersFilteredByTextUseCase)
    factoryOf(::GetFilteredProductsUseCase)
    factoryOf(::GetProductByIdUseCase)
    factoryOf(::GetAllProductSeriesIdsUseCase)
    factoryOf(::GetNotificationByIdUseCase)
    factoryOf(::GetAllNotificationsUseCase)
    factoryOf(::SendNotificationUseCase)
}