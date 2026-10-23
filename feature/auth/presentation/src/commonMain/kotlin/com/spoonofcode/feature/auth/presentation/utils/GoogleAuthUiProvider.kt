package com.spoonofcode.feature.auth.presentation.utils

import com.spoonofcode.feature.auth.presentation.model.GoogleAccount

expect class GoogleAuthUiProvider {
    suspend fun isGoogleAccountAvailable(): Boolean

    suspend fun signIn(): GoogleAccount?

    suspend fun signOut()
}
