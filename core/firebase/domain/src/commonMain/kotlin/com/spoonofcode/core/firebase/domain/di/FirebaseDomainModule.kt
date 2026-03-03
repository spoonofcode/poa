package com.spoonofcode.core.firebase.domain.di

import com.spoonofcode.core.firebase.domain.usecase.SubscribeToFirebaseMessagingTopicUseCase
import com.spoonofcode.core.firebase.domain.usecase.UnsubscribeFromFirebaseMessagingTopicUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseDomainModule = module {
    factoryOf(::SubscribeToFirebaseMessagingTopicUseCase)
    factoryOf(::UnsubscribeFromFirebaseMessagingTopicUseCase)
}