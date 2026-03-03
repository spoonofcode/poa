package com.spoonofcode.core.firebase.di

import com.spoonofcode.core.firebase.usecase.SubscribeToFirebaseMessagingTopicUseCase
import com.spoonofcode.core.firebase.usecase.UnsubscribeFromFirebaseMessagingTopicUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


expect val platformFirebaseModule: Module

val firebaseModule = module {
    includes(platformFirebaseModule)
    factoryOf(::SubscribeToFirebaseMessagingTopicUseCase)
    factoryOf(::UnsubscribeFromFirebaseMessagingTopicUseCase)
}