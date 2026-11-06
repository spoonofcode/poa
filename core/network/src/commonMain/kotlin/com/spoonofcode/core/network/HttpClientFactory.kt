package com.spoonofcode.core.network

import com.spoonofcode.core.logging.POALogger
import com.spoonofcode.core.network.remote.RemoteRefreshTokenDataSource
import com.spoonofcode.core.session.domain.repository.SessionRepository
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

internal class HttpClientFactory(
    private val poaLogger: POALogger,
    private val sessionRepository: SessionRepository,
    private val remoteRefreshTokenDataSource: RemoteRefreshTokenDataSource,
) {
    fun create(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(
                    json =
                        Json {
                            encodeDefaults = true
                            ignoreUnknownKeys = true
                        },
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
            install(Logging) {
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            poaLogger.debug(message)
                        }
                    }
                level = if (BuildKonfig.ENABLE_LOGGING) LogLevel.ALL else LogLevel.NONE
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
                        val accessToken = sessionRepository.getSessionAccessToken()
                        val refreshToken = sessionRepository.getSessionRefreshToken()
                        if (!accessToken.isNullOrBlank()) {
                            BearerTokens(
                                accessToken = accessToken,
                                refreshToken = refreshToken ?: "",
                            )
                        } else {
                            null
                        }
                    }
                    refreshTokens {
                        poaLogger.debug("HttpClientFactory: refreshTokens triggered for URL: ${response.request.url}")

                        if (response.request.url.encodedPath
                                .contains("api/login") ||
                            response.request.url.encodedPath
                                .contains("api/refresh")
                        ) {
                            poaLogger.debug(
                                "HttpClientFactory: Skipping refresh tokens for auth endpoint: ${response.request.url.encodedPath}",
                            )
                            return@refreshTokens null
                        }

                        val refreshToken =
                            sessionRepository
                                .getSessionRefreshToken()
                                ?.takeIf { it.isNotBlank() }
                                ?: oldTokens?.refreshToken?.takeIf { it.isNotBlank() }

                        if (refreshToken.isNullOrBlank()) {
                            poaLogger.warn("HttpClientFactory: Refresh token is null or blank. Clearing session.")
                            sessionRepository.clearSession()
                            return@refreshTokens null
                        }

                        poaLogger.debug("HttpClientFactory: Refreshing tokens using RemoteRefreshTokenDataSource...")
                        var bearerTokens: BearerTokens? = null
                        remoteRefreshTokenDataSource
                            .create(refreshToken)
                            .onSuccess { (newAccessToken, newRefreshToken) ->
                                poaLogger.debug("HttpClientFactory: Token refresh successful. Updating session tokens.")
                                sessionRepository.updateSessionTokens(
                                    accessToken = newAccessToken,
                                    refreshToken = newRefreshToken,
                                )
                                bearerTokens =
                                    BearerTokens(
                                        accessToken = newAccessToken,
                                        refreshToken = newRefreshToken,
                                    )
                            }.onFailure { error ->
                                poaLogger.error(
                                    message = "HttpClientFactory: Token refresh failed: ${error.message}",
                                    throwable = error,
                                )
                                sessionRepository.clearSession()
                            }

                        bearerTokens
                    }
                    sendWithoutRequest { request ->
                        val path = request.url.build().encodedPath
                        !path.contains("api/login") && !path.contains("api/refresh")
                    }
                }
            }
        }
    }
}
