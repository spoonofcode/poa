package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.DispatcherProvider
import com.spoonofcode.poa.core.model.Refresh
import com.spoonofcode.poa.core.network.HttpStatusCodes
import com.spoonofcode.poa.core.network.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

interface RefreshRepository {
    suspend fun refreshToken(jwtRefreshToken: String): Refresh
}

class RefreshRepositoryImpl(
    private val httpClient: HttpClient,
    private val networkConfig: NetworkConfig,
    private val dispatcherProvider: DispatcherProvider,
) : RefreshRepository {
    override suspend fun refreshToken(jwtRefreshToken: String): Refresh {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = httpClient.post("${networkConfig.baseUrl}/refresh/") {
                header(HttpHeaders.Authorization, "Bearer $jwtRefreshToken")
            }

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Refresh.serializer(), responseBody)
        }
    }

    private fun responseOrException(response: HttpResponse): HttpResponse {
        return when (response.status) {
            in HttpStatusCodes.HTTP_SUCCESS_CODES -> return response
            in HttpStatusCodes.HTTP_CLIENT_ERROR_CODES -> throw Exception("Client Error")
            in HttpStatusCodes.HTTP_SERVER_ERROR_CODES -> throw Exception("Server Error")
            else -> unhandledException(response)
        }
    }

    private fun unhandledException(response: HttpResponse): HttpResponse {
        throw Exception("Unhandled Error $response")
    }
}