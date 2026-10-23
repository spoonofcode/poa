package com.spoonofcode.feature.tac.data.di

import com.spoonofcode.feature.tac.data.local.LocalTACDataSource
import com.spoonofcode.feature.tac.data.repository.TACRepositoryImpl
import com.spoonofcode.feature.tac.domain.repository.TACRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val tacDataModule =
    module {
        singleOf(::LocalTACDataSource)
        singleOf(::TACRepositoryImpl).bind<TACRepository>()
    }
