package com.spoonofcode.poa.core.data.base

import com.spoonofcode.poa.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.network.HttpStatusCodes
import com.spoonofcode.poa.core.network.NetworkConfig
import com.spoonofcode.poa.core.network.SessionManager
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.http.contentType
import org.koin.mp.KoinPlatform.getKoin

abstract class BaseRepository(
    private val collectionName: String,
    private val sessionTokenRequired: Boolean = true,
) {
    private val httpClient: HttpClient by getKoin().inject()
    private val networkConfig: NetworkConfig by getKoin().inject()
    private val sessionManager: SessionManager by getKoin().inject()
    private val refreshAccessTokenUseCase: RefreshAccessTokenUseCase by getKoin().inject()

    protected val dispatcherProvider: DispatcherProvider by getKoin().inject()

    protected fun responseOrException(response: HttpResponse): HttpResponse {
        return when (response.status) {
            in HttpStatusCodes.HTTP_SUCCESS_CODES -> return response
            in HttpStatusCodes.HTTP_CLIENT_ERROR_CODES ->
                throw Exception("Client Error ${response.status}")

            in HttpStatusCodes.HTTP_SERVER_ERROR_CODES ->
                throw Exception("Server Error ${response.status}")

            else -> unhandledException(response)
        }
    }

    protected suspend fun doRequest(
        urlPostfixPath: String = "",
        method: HttpMethod,
        queryParams: Map<String, Any> = emptyMap(),
        requestBody: String? = null,
    ): HttpResponse {
        // Common request-setup block
        val requestBuilder: HttpRequestBuilder.() -> Unit = {
            contentType(ContentType.Application.Json)
            url("${networkConfig.baseUrl}/$collectionName/$urlPostfixPath")

            queryParams.forEach { (key, value) -> parameter(key, value) }

            this.method = method

            requestBody?.let {
                setBody(requestBody)
            }
        }

        // Use safeApiCall if token is required, otherwise call the client directly
        return if (sessionTokenRequired) {
            safeApiCall(httpClient, requestBuilder)
        } else {
            httpClient.request(requestBuilder)
        }
    }

    private fun getJwtAccessToken(): String {
        return sessionManager.getSessionAccessToken() ?: "TOKEN_NOT_FOUND"
    }

    private fun unhandledException(response: HttpResponse): HttpResponse {
        throw Exception("Unhandled Error $response")
    }

    /**
     * A generic function that attempts a request with the current access token,
     * retries once upon 401 (after refresh), and returns the response as HttpResponse.
     *
     * You can then deserialize the HttpResponse to whatever data type you need.
     */
    private suspend fun safeApiCall(
        client: HttpClient,
        block: HttpRequestBuilder.() -> Unit
    ): HttpResponse {
        // Local helper to execute the same request with the current (possibly refreshed) token
        suspend fun executeRequest(): HttpResponse {
            return client.request {
                block()
                header(
                    HttpHeaders.Authorization,
                    "Bearer ${getJwtAccessToken()}"
                )
            }
        }

        // 1) First attempt
        var response = executeRequest()

        // 2) If 401, try refresh once
        if (response.status == Unauthorized) {
            val refreshed = refreshAccessTokenUseCase()
            if (!refreshed) {
                // Refresh failed => no valid session
                throw Exception("Client Error")
            }

            // If refresh succeeded => try the same request again
            response = executeRequest()
        }

        return response
    }

}