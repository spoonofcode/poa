package com.spoonofcode.poa.core.network

import com.spoonofcode.poa.core.logging.POALogger
import com.spoonofcode.poa.feature.login.data.remote.RemoteRefreshTokenDataSource
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientFactory(
    private val poaLogger: POALogger,
    private val sessionManager: SessionManager,
    private val remoteRefreshTokenDataSource: RemoteRefreshTokenDataSource,
) {
    fun create(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        encodeDefaults = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        poaLogger.debug(message)
                    }
                }
                level = LogLevel.ALL
            }
            install(WebSockets) {
                pingIntervalMillis = 20_000L
            }

            defaultRequest {
//                header("x-api-key", BuildKonfig.API_KEY)
                contentType(ContentType.Application.Json)
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = sessionManager.getSessionAccessToken()
                        val refreshToken = sessionManager.getSessionRefreshToken()
                        accessToken?.let {
                            BearerTokens(
                                accessToken = it,
                                refreshToken = refreshToken
                            )
                        }
                    }
                    refreshTokens {
                        if (response.request.url.encodedPath.contains("api/login") ||
                            response.request.url.encodedPath.contains("api/refresh")) {
                            return@refreshTokens null
                        }

                        val refreshToken = sessionManager.getSessionRefreshToken()
                        if (refreshToken.isNullOrBlank()) {
                            sessionManager.clearSession()
                            return@refreshTokens null
                        }

                        var bearerTokens: BearerTokens? = null
                        remoteRefreshTokenDataSource.create(refreshToken)
                            .onSuccess { refreshResponse ->
                                sessionManager.saveSessionAccessToken(refreshResponse.jwtAccessToken)
                                sessionManager.saveSessionRefreshToken(refreshResponse.jwtRefreshToken)
                                bearerTokens = BearerTokens(
                                    accessToken = refreshResponse.jwtAccessToken,
                                    refreshToken = refreshResponse.jwtRefreshToken,
                                )
                            }.onFailure { error ->
                                sessionManager.clearSession()
                            }

                        bearerTokens
                    }
                }
            }
        }
    }
}