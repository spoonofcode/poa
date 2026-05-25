package com.spoonofcode.core.network

import com.spoonofcode.core.session.domain.repository.LogoutHandler
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.clearAuthTokens
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HttpLogoutHandler : LogoutHandler, KoinComponent {
    private val httpClient: HttpClient by inject()

    override suspend fun onLogout() {
        httpClient.clearAuthTokens()
    }
}
