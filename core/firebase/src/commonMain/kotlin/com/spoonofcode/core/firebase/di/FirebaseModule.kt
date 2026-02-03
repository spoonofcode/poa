package com.spoonofcode.core.firebase.di

import org.koin.core.module.Module
import org.koin.dsl.module


expect val platformFirebaseModule: Module

val firebaseModule = module {
    includes(platformFirebaseModule)
}