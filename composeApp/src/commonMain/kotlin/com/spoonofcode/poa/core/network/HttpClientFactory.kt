package com.spoonofcode.poa.core.network

import com.spoonofcode.poa.core.logging.POALogger
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientFactory(
    private val poaLogger: POALogger,
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

            // TODO #34-Try use custom AuthPlugin instead of safeApiCall function
//            install(AuthPlugin) {
//                // Provide a suspend function that does your refresh call
//                refreshTokenFunc = {
//                    getKoin().get<TokenUseCase>().refreshAccessToken()
//                }
//            }
        }
    }
}