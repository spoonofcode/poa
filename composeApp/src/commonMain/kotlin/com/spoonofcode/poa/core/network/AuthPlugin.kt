package com.spoonofcode.poa.core.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.util.*
import io.ktor.http.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


// TODO #34-Try use custom AuthPlugin instead of safeApiCall function
/**
 * A simple custom plugin that checks for 401 in the received response
 * and attempts a token refresh before re-trying the request.
 */
class AuthPlugin(
    private val refreshTokenFunc: suspend () -> Boolean
) {

    private val mutex = Mutex()

    companion object : HttpClientPlugin<AuthPluginConfig, AuthPlugin> {

        override val key: AttributeKey<AuthPlugin> = AttributeKey("AuthPlugin")

        override fun prepare(block: AuthPluginConfig.() -> Unit): AuthPlugin {
            val config = AuthPluginConfig().apply(block)
            return AuthPlugin(config.refreshTokenFunc)
        }

        override fun install(plugin: AuthPlugin, scope: HttpClient) {
            scope.receivePipeline.intercept(HttpReceivePipeline.Before) { httpResponse ->
                if (httpResponse.status == HttpStatusCode.Unauthorized) {

                    // We can attempt a refresh under a mutex to avoid parallel refresh calls
                    val refreshSuccessful = plugin.mutex.withLock {
                        plugin.refreshTokenFunc()
                    }

                    if (refreshSuccessful) {
                        // If refresh worked, we want to "retry" the original request with the new token.
                        // The tricky part: You must re-execute the pipeline from scratch with the new token.
                        // This is more advanced logic than a simple code snippet can show in full detail.

                        // For demonstration, we can throw a special exception or return a placeholder
                        // so that we can re-call the request at a higher level. We'll keep it simple here.
                        throw RefreshSucceededRetryException()
                    } else {
                        // Refresh failed => Let the pipeline continue as 401,
                        // or handle forced logout, etc.
                    }
                }

                // If not 401, just proceed normally
                proceedWith(httpResponse)
            }
        }
    }
}

/**
 * Configuration class for the plugin.
 */
class AuthPluginConfig {
    var refreshTokenFunc: suspend () -> Boolean = { false }
}

/**
 * Custom exception we can throw to signal "refresh succeeded, retry original call."
 * This can be caught in a higher-level wrapper if you want to fully re-send the request.
 */
class RefreshSucceededRetryException : RuntimeException("Refresh succeeded. Retry needed.")