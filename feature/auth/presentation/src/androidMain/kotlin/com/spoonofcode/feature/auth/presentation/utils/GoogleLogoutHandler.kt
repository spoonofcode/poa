package com.spoonofcode.feature.auth.presentation.utils

import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import com.spoonofcode.core.data.logging.POALogger
import com.spoonofcode.core.session.domain.repository.LogoutHandler

class GoogleLogoutHandler(
    private val credentialManager: CredentialManager,
    private val poaLogger: POALogger,
) : LogoutHandler {
    override suspend fun onLogout() {
        poaLogger.info("GoogleLogoutHandler: Clearing credential state (Android)")
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}
