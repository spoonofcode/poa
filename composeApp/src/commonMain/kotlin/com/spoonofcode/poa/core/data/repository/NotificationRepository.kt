package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.model.NotificationRequest
import com.spoonofcode.poa.core.model.ProductRequest
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@OpenForMokkery
class NotificationRepository : BaseRepository(
    collectionName = "notifications",
) {
    suspend fun create(
        notification: NotificationRequest,
    ): Boolean {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                method = HttpMethod.Post,
                requestBody =
                    Json.encodeToString(
                        NotificationRequest.serializer(),
                        notification,
                    )
            )
            responseOrException(response).body<String>()
            true
        }
    }

    suspend fun read(
        notificationId: String,
    ): Notification {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = notificationId,
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Notification.serializer(), responseBody)
        }
    }

    suspend fun readAll(): List<Notification> {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(Notification.serializer()), responseBody)
        }
    }
}