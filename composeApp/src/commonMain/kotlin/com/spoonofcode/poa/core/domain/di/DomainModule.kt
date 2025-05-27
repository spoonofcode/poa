package com.spoonofcode.poa.core.domain.di

import com.spoonofcode.poa.core.domain.AddRoleToUserUseCase
import com.spoonofcode.poa.core.domain.GetAllUsersUseCase
import com.spoonofcode.poa.core.domain.GetFilteredNFTInfosByTextUseCase
import com.spoonofcode.poa.core.domain.GetFilteredNFTInfosUseCase
import com.spoonofcode.poa.core.domain.GetNFTInfoByIdUseCase
import com.spoonofcode.poa.core.domain.GetProfileUseCase
import com.spoonofcode.poa.core.domain.GetRolesByUserIdUseCase
import com.spoonofcode.poa.core.domain.GetUserByIdUseCase
import com.spoonofcode.poa.core.domain.HasSpecialSettingsUseCase
import com.spoonofcode.poa.core.domain.LoginGoogleUseCase
import com.spoonofcode.poa.core.domain.LoginUseCase
import com.spoonofcode.poa.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.domain.RegisterUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::LoginUseCase)
    singleOf(::LoginGoogleUseCase)
    singleOf(::RegisterUseCase)
    singleOf(::RefreshAccessTokenUseCase)
    singleOf(::GetProfileUseCase)
    singleOf(::GetRolesByUserIdUseCase)
    singleOf(::GetAllUsersUseCase)
    singleOf(::AddRoleToUserUseCase)
    singleOf(::GetUserByIdUseCase)
    singleOf(::HasSpecialSettingsUseCase)
    singleOf(::GetFilteredNFTInfosUseCase)
    singleOf(::GetFilteredNFTInfosByTextUseCase)
    singleOf(::GetNFTInfoByIdUseCase)
}