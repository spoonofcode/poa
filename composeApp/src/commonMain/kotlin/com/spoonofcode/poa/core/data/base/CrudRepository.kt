package com.spoonofcode.poa.core.data.base

import com.spoonofcode.poa.core.domain.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.network.HttpStatusCodes
import com.spoonofcode.poa.core.network.NetworkConfig
import com.spoonofcode.poa.core.network.SessionManager
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatform.getKoin

interface CrudRepository<RQ, RS> {
    suspend fun create(request: RQ): RS
    suspend fun read(id: Int): RS
    suspend fun update(id: Int, request: RQ): Boolean
    suspend fun delete(id: Int): Boolean
    suspend fun readAll(): List<RS>
    suspend fun doRequest(
        urlPath: String,
        method: HttpMethod,
        sessionTokenRequired: Boolean = true,
        requestBody: RQ? = null,
        customRequestBody: String? = null,
        queryParams: Map<String, String> = emptyMap()
    ): HttpResponse
}

abstract class GenericCrudRepository<RQ : Any, RS : Any>(
    private val resourceName: String,
    private val requestSerializer: KSerializer<RQ>,
    private val responseSerializer: KSerializer<RS>,
    private val sessionTokenRequired: Boolean = true,
) : CrudRepository<RQ, RS> {
    private val httpClient: HttpClient by getKoin().inject()
    private val networkConfig: NetworkConfig by getKoin().inject()
    private val sessionManager: SessionManager by getKoin().inject()
    private val refreshAccessTokenUseCase: RefreshAccessTokenUseCase by getKoin().inject()

    override suspend fun create(request: RQ): RS {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/",
                method = HttpMethod.Post,
                sessionTokenRequired = sessionTokenRequired,
                requestBody = request,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(responseSerializer, responseBody)
        }
    }

    override suspend fun read(id: Int): RS {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/$id",
                method = HttpMethod.Get,
                sessionTokenRequired = sessionTokenRequired
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(responseSerializer, responseBody)
        }
    }

    override suspend fun update(id: Int, request: RQ): Boolean {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/$id",
                method = HttpMethod.Put,
                sessionTokenRequired = sessionTokenRequired,
                requestBody = request
            )

            responseOrException(response).body<String>()
            true
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/$id",
                method = HttpMethod.Delete,
                sessionTokenRequired = sessionTokenRequired
            )

            responseOrException(response).body<String>()
            true
        }
    }

    override suspend fun readAll(): List<RS> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/",
                method = HttpMethod.Get,
                sessionTokenRequired = sessionTokenRequired
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(responseSerializer), responseBody)
        }
    }

    open fun responseOrException(response: HttpResponse): HttpResponse {
        return when (response.status) {
            in HttpStatusCodes.HTTP_SUCCESS_CODES -> return response
            in HttpStatusCodes.HTTP_CLIENT_ERROR_CODES ->
                throw Exception("Client Error ${response.status}")

            in HttpStatusCodes.HTTP_SERVER_ERROR_CODES ->
                throw Exception("Server Error ${response.status}")

            else -> unhandledException(response)
        }
    }

    private fun unhandledException(response: HttpResponse): HttpResponse {
        throw Exception("Unhandled Error $response")
    }

    private fun getJwtAccessToken(): String {
        return sessionManager.getSessionAccessToken() ?: "TOKEN_NOT_FOUND"
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

    override suspend fun doRequest(
        urlPath: String,
        method: HttpMethod,
        sessionTokenRequired: Boolean,
        requestBody: RQ?,
        customRequestBody: String?,
        queryParams: Map<String, String>,
    ): HttpResponse {
        // Common request-setup block
        val requestBuilder: HttpRequestBuilder.() -> Unit = {
            contentType(ContentType.Application.Json)
            url("${networkConfig.baseUrl}/$urlPath")

            queryParams.forEach { (key, value) -> parameter(key, value) }

            this.method = method

            customRequestBody?.let {
                setBody(customRequestBody)
            }

            requestBody?.let { body ->
                setBody(Json.encodeToString(requestSerializer, body))
            }
        }

        // Use safeApiCall if token is required, otherwise call the client directly
        return if (sessionTokenRequired) {
            safeApiCall(httpClient, requestBuilder)
        } else {
            httpClient.request(requestBuilder)
        }
    }

}