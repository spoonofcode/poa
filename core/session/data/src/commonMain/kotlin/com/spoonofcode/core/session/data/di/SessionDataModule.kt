package com.spoonofcode.core.session.data.di

import com.spoonofcode.core.session.data.SessionRepositoryImpl
import com.spoonofcode.core.session.domain.SessionRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sessionDataModule = module {
    singleOf(::SessionRepositoryImpl).bind<SessionRepository>()
}