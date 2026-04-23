package com.spoonofcode.core.data.storage.di

import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformStorageModule: Module

val storageModule: Module = module {
    includes(platformStorageModule)
}
