package com.spoonofcode.poa.core.ui.utils

import com.spoonofcode.poa.feature.login.domain.model.GoogleAccount

expect class GoogleAuthUiProvider {
    suspend fun signIn(): GoogleAccount?
    suspend fun signOut()
}