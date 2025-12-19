package com.spoonofcode.poa.core.domain.di

import com.spoonofcode.poa.core.domain.DeleteAccountUseCase
import com.spoonofcode.poa.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.domain.user.GetUserByIdUseCase
import com.spoonofcode.poa.core.domain.user.SubscribeUserTopicsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::DeleteAccountUseCase)
    factoryOf(::RefreshAccessTokenUseCase)
    factoryOf(::GetUserByIdUseCase)

    factoryOf(::SubscribeUserTopicsUseCase)
}