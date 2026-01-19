package com.spoonofcode.poa.core.data.base

import com.spoonofcode.poa.core.coroutines.DispatcherProvider
import com.spoonofcode.poa.core.data.base.model.Refresh
import com.spoonofcode.poa.core.network.HttpStatusCodes
import com.spoonofcode.poa.core.network.NetworkConfig
import com.spoonofcode.poa.core.network.SessionManager
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.http.contentType
import io.ktor.util.reflect.typeInfo
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatform.getKoin

abstract class RemoteBaseDataSource(
    private val collectionName: String,
    private val sessionTokenRequired: Boolean = true,
) {
    private val httpClient: HttpClient by getKoin().inject()
    private val networkConfig: NetworkConfig by getKoin().inject()
    private val dispatcherProvider: DispatcherProvider by getKoin().inject()
    private val sessionManager: SessionManager by getKoin().inject()

    internal suspend inline fun <reified T> doRequest(
        urlPostfixPath: String = "",
        method: HttpMethod,
        queryParams: Map<String, Any> = emptyMap(),
        requestBody: Any? = null,
    ): Result<T> = withContext(dispatcherProvider.io()) {
        try {
            val urlString = "${networkConfig.baseUrl}/$collectionName/$urlPostfixPath"

            val requestBuilder: HttpRequestBuilder.() -> Unit = {
                contentType(ContentType.Application.Json)
                url(urlString)

                queryParams.forEach { (key, value) -> parameter(key, value) }

                this.method = method

                requestBody?.let {
                    setBody(it)
                }
            }

            val response = if (sessionTokenRequired) {
                safeApiCall(httpClient, requestBuilder)
            } else {
                httpClient.request(requestBuilder)
            }

            Result.success(responseOrException(response).body(typeInfo<T>()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun getJwtAccessToken(): String =
        sessionManager.getSessionAccessToken() ?: "TOKEN_NOT_FOUND"

    private fun unhandledException(response: HttpResponse): HttpResponse {
        throw Exception("Unhandled Error $response")
    }

    private fun responseOrException(response: HttpResponse): HttpResponse {
        return when (response.status) {
            in HttpStatusCodes.HTTP_SUCCESS_CODES -> return response
            in HttpStatusCodes.HTTP_CLIENT_ERROR_CODES ->
                throw Exception("Client Error ${response.status}")

            in HttpStatusCodes.HTTP_SERVER_ERROR_CODES ->
                throw Exception("Server Error ${response.status}")

            else -> unhandledException(response)
        }
    }

    /**
     * A generic function that attempts a request with the current access token,
     * retries once upon 401 (after refresh), and returns the response as HttpResponse.
     *
     * You can then deserialize the HttpResponse to whatever data type you need.
     */
    private suspend fun safeApiCall(
        client: HttpClient,
        block: HttpRequestBuilder.() -> Unit,
    ): HttpResponse {
        val jwtAccessToken = getJwtAccessToken()

        // Local helper to execute the same request with the current (possibly refreshed) token
        suspend fun executeRequest(): HttpResponse {
            return client.request {
                block()
                header(
                    HttpHeaders.Authorization,
                    "Bearer $jwtAccessToken"
                )
            }
        }

        // 1) First attempt
        var response = executeRequest()

        // 2) If 401, try refresh once
        if (response.status == Unauthorized) {
            val refreshed = refreshAccessToken()
            if (!refreshed) {
                // Refresh failed => no valid session
                throw Exception("Client Error")
            }

            // If refresh succeeded => try the same request again
            response = executeRequest()
        }

        return response
    }

    private suspend fun refreshAccessToken(): Boolean {
        val jwtRefreshToken = sessionManager.getSessionRefreshToken() ?: return false
        val refresh = refreshToken(jwtRefreshToken)
        sessionManager.saveSessionAccessToken(refresh.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(refresh.jwtRefreshToken)
        return true
    }

    private suspend fun refreshToken(jwtRefreshToken: String): Refresh =
        withContext(dispatcherProvider.io()) {
            val refreshUrlString = "${networkConfig.baseUrl}/api/refresh/"
            val response: HttpResponse = httpClient.post(refreshUrlString) {
                header(HttpHeaders.Authorization, "Bearer $jwtRefreshToken")
            }

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Refresh.serializer(), responseBody)
        }
}