package com.spoonofcode.core.firebase.data.di

import org.koin.core.module.Module
import org.koin.dsl.module


expect val platformFirebaseDataModule: Module

val firebaseDataModule = module {
    includes(platformFirebaseDataModule)
}