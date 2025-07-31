package com.spoonofcode.poa.core.data.di

import com.spoonofcode.poa.core.data.repository.LoginGoogleRepository
import com.spoonofcode.poa.core.data.repository.LoginRepository
import com.spoonofcode.poa.core.data.repository.MessageFCMRepository
import com.spoonofcode.poa.core.data.repository.ProductFilterRepository
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.data.repository.ProfileRepository
import com.spoonofcode.poa.core.data.repository.RefreshRepository
import com.spoonofcode.poa.core.data.repository.RegisterRepository
import com.spoonofcode.poa.core.data.repository.RoleRepository
import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.data.repository.old.BlockchainRepository
import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterRepository
import com.spoonofcode.poa.core.data.repository.old.NFTInfoRepository
import com.spoonofcode.poa.core.data.repository.old.NFTSerieRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::LoginGoogleRepository)
    singleOf(::LoginRepository)
    singleOf(::RefreshRepository)
    singleOf(::UserRepository)
    singleOf(::RegisterRepository)
    singleOf(::ProductFilterRepository)
    singleOf(::ProductRepository)
    singleOf(::ProfileRepository)
    singleOf(::RoleRepository)
    singleOf(::NFTInfoFilterRepository)
    singleOf(::NFTInfoRepository)
    singleOf(::NFTSerieRepository)
    singleOf(::BlockchainRepository)
    singleOf(::MessageFCMRepository)
}