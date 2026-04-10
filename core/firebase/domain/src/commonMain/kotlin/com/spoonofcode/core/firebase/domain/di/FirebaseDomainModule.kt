package com.spoonofcode.core.firebase.domain.di

import com.spoonofcode.core.firebase.domain.usecase.FirebaseMessagingSubscribeToTopicsUseCase
import com.spoonofcode.core.firebase.domain.usecase.FirebaseMessagingUnsubscribeFromTopicsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseDomainModule = module {
    factoryOf(::FirebaseMessagingSubscribeToTopicsUseCase)
    factoryOf(::FirebaseMessagingUnsubscribeFromTopicsUseCase)
}