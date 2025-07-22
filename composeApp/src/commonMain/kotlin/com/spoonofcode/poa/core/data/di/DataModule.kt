package com.spoonofcode.poa.core.data.di

import com.spoonofcode.poa.core.data.repository.FilterRepository
import com.spoonofcode.poa.core.data.repository.LoginGoogleRepository
import com.spoonofcode.poa.core.data.repository.LoginRepository
import com.spoonofcode.poa.core.data.repository.NFTInfoRepository
import com.spoonofcode.poa.core.data.repository.NFTSerieRepository
import com.spoonofcode.poa.core.data.repository.ProfileRepository
import com.spoonofcode.poa.core.data.repository.RefreshRepository
import com.spoonofcode.poa.core.data.repository.RegisterRepository
import com.spoonofcode.poa.core.data.repository.RoleRepository
import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.data.repository.BlockchainRepository
import com.spoonofcode.poa.core.data.repository.MessageFCMRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::LoginGoogleRepository)
    singleOf(::LoginRepository)
    singleOf(::RefreshRepository)
    singleOf(::UserRepository)
    singleOf(::RegisterRepository)
    singleOf(::ProfileRepository)
    singleOf(::RoleRepository)
    singleOf(::FilterRepository)
    singleOf(::NFTInfoRepository)
    singleOf(::NFTSerieRepository)
    singleOf(::BlockchainRepository)
    singleOf(::MessageFCMRepository)
}