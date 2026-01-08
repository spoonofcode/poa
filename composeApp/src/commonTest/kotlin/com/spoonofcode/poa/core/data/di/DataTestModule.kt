package com.spoonofcode.poa.core.data.di

import com.spoonofcode.poa.core.data.repository.faqRepositoryMock
import com.spoonofcode.poa.core.data.repository.loginRepositoryMock
import com.spoonofcode.poa.core.data.repository.notificationRepositoryMock
import com.spoonofcode.poa.core.data.repository.partnerRepositoryMock
import com.spoonofcode.poa.core.data.repository.productRepositoryMock
import com.spoonofcode.poa.core.data.repository.profileRepositoryMock
import com.spoonofcode.poa.core.data.repository.userRepositoryMock
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataTestModule = module {
    singleOf(::loginRepositoryMock)
    singleOf(::productRepositoryMock)
    singleOf(::profileRepositoryMock)
    singleOf(::userRepositoryMock)
    singleOf(::faqRepositoryMock)
    singleOf(::notificationRepositoryMock)
    singleOf(::partnerRepositoryMock)
}