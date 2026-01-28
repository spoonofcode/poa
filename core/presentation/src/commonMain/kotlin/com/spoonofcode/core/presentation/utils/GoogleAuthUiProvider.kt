package com.spoonofcode.core.presentation.utils

import com.spoonofcode.core.presentation.domain.model.GoogleAccount

// TODO #109 Fix after migration to feature modules
expect class GoogleAuthUiProvider {
    suspend fun signIn(): GoogleAccount?
    suspend fun signOut()
}