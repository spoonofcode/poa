package com.spoonofcode.poa.core.domain.di

import com.spoonofcode.poa.core.domain.AddRoleToUserUseCase
import com.spoonofcode.poa.core.domain.DeleteAccountUseCase
import com.spoonofcode.poa.core.domain.GetAllUsersUseCase
import com.spoonofcode.poa.core.domain.product.GetFilteredProductsByTextUseCase
import com.spoonofcode.poa.core.domain.product.GetFilteredProductsUseCase
import com.spoonofcode.poa.core.domain.product.GetProductByIdUseCase
import com.spoonofcode.poa.core.domain.GetProfileUseCase
import com.spoonofcode.poa.core.domain.GetRolesByUserIdUseCase
import com.spoonofcode.poa.core.domain.GetUserByIdUseCase
import com.spoonofcode.poa.core.domain.HasSpecialSettingsUseCase
import com.spoonofcode.poa.core.domain.login.LoginGoogleUseCase
import com.spoonofcode.poa.core.domain.login.LoginUseCase
import com.spoonofcode.poa.core.domain.login.LogoutUseCase
import com.spoonofcode.poa.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.domain.login.RegisterUseCase
import com.spoonofcode.poa.core.domain.SendMessageFCMUseCase
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
    factoryOf(::GetFilteredProductsUseCase)
    factoryOf(::GetProductByIdUseCase)
}