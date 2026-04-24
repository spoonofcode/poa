package com.spoonofcode.core.network.base

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.core.network.HttpStatusCodes
import com.spoonofcode.core.network.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.AuthCircuitBreaker
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.util.reflect.typeInfo
import kotlinx.coroutines.withContext
import org.koin.mp.KoinPlatform.getKoin

abstract class RemoteBaseDataSource(
    val collectionName: String,
) {
    val httpClient: HttpClient by getKoin().inject()
    val networkConfig: NetworkConfig by getKoin().inject()
    val dispatcherProvider: DispatcherProvider by getKoin().inject()

    suspend inline fun <reified T> doRequest(
        urlPostfixPath: String = "",
        method: HttpMethod,
        queryParams: Map<String, Any> = emptyMap(),
        requestBody: Any? = null,
        markAsRefreshTokenRequest: Boolean = false,
    ): Result<T> = withContext(dispatcherProvider.io()) {
        try {
            val urlString =
                if (urlPostfixPath.isNotEmpty())
                    "${networkConfig.baseUrl}/$collectionName/$urlPostfixPath"
                else
                    "${networkConfig.baseUrl}/$collectionName"

            val requestBuilder: HttpRequestBuilder.() -> Unit = {
                contentType(ContentType.Application.Json)
                url(urlString)

                queryParams.forEach { (key, value) -> parameter(key, value) }

                this.method = method

                requestBody?.let {
                    setBody(it)
                }
                if (markAsRefreshTokenRequest) {
                    attributes.put(AuthCircuitBreaker, Unit)
                }
            }

            val response = httpClient.request(requestBuilder)

            Result.success(responseOrException(response).body(typeInfo<T>()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun unhandledException(response: HttpResponse): HttpResponse {
        throw Exception("Unhandled Error $response")
    }

    fun responseOrException(response: HttpResponse): HttpResponse {
        return when (response.status) {
            in HttpStatusCodes.HTTP_SUCCESS_CODES -> return response
            in HttpStatusCodes.HTTP_CLIENT_ERROR_CODES ->
                throw Exception("Client Error ${response.status}")

            in HttpStatusCodes.HTTP_SERVER_ERROR_CODES ->
                throw Exception("Server Error ${response.status}")

            else -> unhandledException(response)
        }
    }
}