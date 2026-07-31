package com.spoonofcode.feature.auth.presentation.utils

import cocoapods.GoogleSignIn.GIDSignIn
import com.spoonofcode.core.data.logging.POALogger
import com.spoonofcode.core.session.domain.repository.LogoutHandler
import kotlinx.cinterop.ExperimentalForeignApi

class GoogleLogoutHandler(
    private val poaLogger: POALogger
) : LogoutHandler {
    @OptIn(ExperimentalForeignApi::class)
    override suspend fun onLogout() {
        poaLogger.info("GoogleLogoutHandler: Signing out from Google (iOS)")
        GIDSignIn.sharedInstance.signOut()
    }
}
