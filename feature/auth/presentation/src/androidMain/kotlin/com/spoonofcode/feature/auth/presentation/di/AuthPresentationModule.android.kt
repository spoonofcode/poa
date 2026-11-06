package com.spoonofcode.feature.auth.presentation.di

import androidx.credentials.CredentialManager
import androidx.credentials.CredentialManager.Companion.create
import com.spoonofcode.core.session.domain.repository.LogoutHandler
import com.spoonofcode.feature.auth.presentation.utils.GoogleAuthProvider
import com.spoonofcode.feature.auth.presentation.utils.GoogleLogoutHandler
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformAuthPresentationModule =
    module {
        factory { create(androidContext()) } bind CredentialManager::class
        factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class
        factoryOf(::GoogleLogoutHandler) bind LogoutHandler::class
    }
