package com.spoonofcode.poa.feature.login.di

import androidx.credentials.CredentialManager
import androidx.credentials.CredentialManager.Companion.create
import com.spoonofcode.poa.core.ui.utils.GoogleAuthProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformLoginModule = module {
    factory { create(androidContext()) } bind CredentialManager::class
    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class
}