package com.spoonofcode.poa.core.domain.di

import com.spoonofcode.poa.core.domain.AddRoleToUserUseCase
import com.spoonofcode.poa.core.domain.DeleteAccountUseCase
import com.spoonofcode.poa.core.domain.GetAllUsersUseCase
import com.spoonofcode.poa.core.domain.GetFilteredProductsByTextUseCase
import com.spoonofcode.poa.core.domain.GetFilteredProductsUseCase
import com.spoonofcode.poa.core.domain.GetProductByIdUseCase
import com.spoonofcode.poa.core.domain.old.GetFilteredNFTInfosByTextUseCase
import com.spoonofcode.poa.core.domain.old.GetFilteredNFTInfosUseCase
import com.spoonofcode.poa.core.domain.old.GetNFTInfoByIdUseCase
import com.spoonofcode.poa.core.domain.GetProfileUseCase
import com.spoonofcode.poa.core.domain.GetRolesByUserIdUseCase
import com.spoonofcode.poa.core.domain.GetUserByIdUseCase
import com.spoonofcode.poa.core.domain.HasSpecialSettingsUseCase
import com.spoonofcode.poa.core.domain.LoginGoogleUseCase
import com.spoonofcode.poa.core.domain.LoginUseCase
import com.spoonofcode.poa.core.domain.LogoutUseCase
import com.spoonofcode.poa.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.domain.RegisterUseCase
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
    factoryOf(::GetFilteredNFTInfosUseCase)
    factoryOf(::GetFilteredNFTInfosByTextUseCase)
    factoryOf(::GetNFTInfoByIdUseCase)
    factoryOf(::SendMessageFCMUseCase)
    factoryOf(::GetFilteredProductsByTextUseCase)
    factoryOf(::GetFilteredProductsUseCase)
    factoryOf(::GetProductByIdUseCase)
}