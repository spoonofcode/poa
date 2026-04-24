package com.spoonofcode.core.storage.data.di

import eu.anifantakis.lib.ksafe.KSafe
import org.koin.dsl.module

actual val platformStorageDataModule = module {
    single { KSafe() }
}
