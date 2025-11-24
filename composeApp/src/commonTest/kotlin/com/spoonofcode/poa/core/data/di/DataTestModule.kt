package com.spoonofcode.poa.core.data.di

import com.spoonofcode.poa.core.data.repository.loginGoogleRepositoryMock
import com.spoonofcode.poa.core.data.repository.loginRepositoryMock
import com.spoonofcode.poa.core.data.repository.productRepositoryMock
import com.spoonofcode.poa.core.data.repository.profileRepositoryMock
import com.spoonofcode.poa.core.data.repository.registerRepositoryMock
import com.spoonofcode.poa.core.data.repository.userRepositoryMock
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataTestModule = module {
    singleOf(::loginRepositoryMock)
    singleOf(::loginGoogleRepositoryMock)
    singleOf(::productRepositoryMock)
    singleOf(::profileRepositoryMock)
    singleOf(::registerRepositoryMock)
    singleOf(::userRepositoryMock)
}