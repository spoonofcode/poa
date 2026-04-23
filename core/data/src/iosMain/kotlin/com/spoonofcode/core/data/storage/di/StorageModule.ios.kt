package com.spoonofcode.core.data.storage.di

import eu.anifantakis.lib.ksafe.KSafe
import org.koin.dsl.module

actual val platformStorageModule = module {
    single { KSafe() }
}
