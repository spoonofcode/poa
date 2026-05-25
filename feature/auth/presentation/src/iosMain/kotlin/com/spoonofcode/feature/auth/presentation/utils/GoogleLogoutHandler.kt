package com.spoonofcode.feature.auth.presentation.utils

import cocoapods.GoogleSignIn.GIDSignIn
import com.spoonofcode.core.session.domain.repository.LogoutHandler
import kotlinx.cinterop.ExperimentalForeignApi

class GoogleLogoutHandler : LogoutHandler {
    @OptIn(ExperimentalForeignApi::class)
    override suspend fun onLogout() {
        GIDSignIn.sharedInstance.signOut()
    }
}
