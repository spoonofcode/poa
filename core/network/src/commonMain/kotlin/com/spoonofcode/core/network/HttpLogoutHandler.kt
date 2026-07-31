package com.spoonofcode.core.network

import com.spoonofcode.core.data.logging.POALogger
import com.spoonofcode.core.session.domain.repository.LogoutHandler
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.clearAuthTokens

class HttpLogoutHandler(
    private val httpClient: HttpClient,
    private val poaLogger: POALogger,
) : LogoutHandler {
    override suspend fun onLogout() {
        poaLogger.info("HttpLogoutHandler: Clearing auth tokens")
        httpClient.clearAuthTokens()
    }
}
