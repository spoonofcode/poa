package com.spoonofcode.feature.scan.nfc.data.remote

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.core.logging.POALogger
import com.spoonofcode.core.network.HttpStatusCodes
import com.spoonofcode.feature.scan.nfc.data.model.NFCTagResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.util.reflect.typeInfo
import kotlinx.coroutines.withContext

internal class RemoteNFCTagDataSource(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider,
    private val poaLogger: POALogger,
) {
    suspend fun readNFCTagPublicId(url: String): Result<NFCTagResponse> {
        val formattedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "https://$url"
        } else {
            url
        }.filterNot { it.isWhitespace() || it.isISOControl() }

        poaLogger.info("RemoteNFCTagDataSource: Reading NFC tag from URL: $formattedUrl")

        return doRequest(
            urlString = formattedUrl,
            method = HttpMethod.Get,
        )
    }

    private suspend inline fun <reified T> doRequest(
        urlString: String = "",
        method: HttpMethod,
        requestBody: Any? = null,
    ): Result<T> = withContext(dispatcherProvider.io()) {
        try {
            val requestBuilder: HttpRequestBuilder.() -> Unit = {
                contentType(ContentType.Application.Json)
                url(urlString)

                this.method = method

                requestBody?.let {
                    setBody(it)
                }
            }

            val response = httpClient.request(requestBuilder)
            val resultBody = responseOrException(response).body(typeInfo<T>()) as T
            
            poaLogger.debug("RemoteNFCTagDataSource: Request successful for $urlString")
            Result.success(resultBody)
        } catch (e: Exception) {
            poaLogger.error("RemoteNFCTagDataSource: Request failed for $urlString", e)
            Result.failure(e)
        }
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

    private fun unhandledException(response: HttpResponse): HttpResponse {
        throw Exception("Unhandled Error $response")
    }
}