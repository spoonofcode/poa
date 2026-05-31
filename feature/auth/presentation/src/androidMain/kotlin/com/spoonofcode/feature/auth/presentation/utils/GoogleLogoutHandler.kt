package com.spoonofcode.feature.auth.presentation.utils

import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import com.spoonofcode.core.session.domain.repository.LogoutHandler

class GoogleLogoutHandler(
    private val credentialManager: CredentialManager
) : LogoutHandler {
    override suspend fun onLogout() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}
