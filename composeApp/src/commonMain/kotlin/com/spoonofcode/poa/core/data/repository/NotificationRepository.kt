package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.model.NotificationRequest
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class NotificationRepository : GenericCrudRepository<NotificationRequest, Notification>(
    resourceName = "notifications",
    requestSerializer = NotificationRequest.serializer(),
    responseSerializer = Notification.serializer(),
) {
    suspend fun sendNotification(body: NotificationRequest) {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "notifications/send",
                method = HttpMethod.Post,
                customRequestBody = Json.encodeToString(
                    NotificationRequest.serializer(),
                    NotificationRequest(
                        title = body.title,
                        text = body.text,
                        imageUrl = body.imageUrl,
                        seriesIds = body.seriesIds,
                    )
                ),
            )
            responseOrException(response).body<String>()
        }
    }
}