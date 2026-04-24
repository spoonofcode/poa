package com.spoonofcode.core.storage.data.di

import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformStorageDataModule: Module

val storageDataModule: Module = module {
    includes(platformStorageDataModule)
}
