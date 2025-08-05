package com.spoonofcode.poa.core.data.di

import com.spoonofcode.poa.core.data.repository.LoginGoogleRepository
import com.spoonofcode.poa.core.data.repository.LoginRepository
import com.spoonofcode.poa.core.data.repository.MessageFCMRepository
import com.spoonofcode.poa.core.data.repository.PartnerCategoryRepository
import com.spoonofcode.poa.core.data.repository.PartnerFilterRepository
import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.data.repository.ProductFilterRepository
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.data.repository.ProfileRepository
import com.spoonofcode.poa.core.data.repository.RefreshRepository
import com.spoonofcode.poa.core.data.repository.RegisterRepository
import com.spoonofcode.poa.core.data.repository.RoleRepository
import com.spoonofcode.poa.core.data.repository.UserRepository
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
    singleOf(::PartnerRepository)
    singleOf(::PartnerCategoryRepository)
    singleOf(::PartnerFilterRepository)
    singleOf(::ProfileRepository)
    singleOf(::RoleRepository)
    singleOf(::MessageFCMRepository)
}