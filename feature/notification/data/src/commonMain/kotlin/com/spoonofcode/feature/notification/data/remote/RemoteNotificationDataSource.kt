package com.spoonofcode.feature.notification.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.notification.data.remote.model.NotificationRequest
import com.spoonofcode.feature.notification.data.remote.model.NotificationResponse
import io.ktor.http.HttpMethod
import kotlin.time.Instant

class RemoteNotificationDataSource : RemoteBaseDataSource(
    collectionName = "api/notifications",
) {
    suspend fun create(
        title: String,
        text: String,
        link: String?,
        topics: List<String>,
        deliveryDateTime: Instant,
        expirationDateTime: Instant,
        ownerId: String,
    ): Result<NotificationResponse> = doRequest(
        method = HttpMethod.Post,
        requestBody = NotificationRequest(
            title = title,
            text = text,
            link = link,
            topics = topics,
            deliveryDateTime = deliveryDateTime,
            expirationDateTime = expirationDateTime,
            ownerId = ownerId,
        ),
    )

    suspend fun update(
        notificationId: String,
        title: String,
        text: String,
        link: String?,
        topics: List<String>,
        deliveryDateTime: Instant,
        expirationDateTime: Instant,
        ownerId: String,
    ): Result<Unit> = doRequest(
        urlPostfixPath = notificationId,
        method = HttpMethod.Put,
        requestBody = NotificationRequest(
            title = title,
            text = text,
            link = link,
            topics = topics,
            deliveryDateTime = deliveryDateTime,
            expirationDateTime = expirationDateTime,
            ownerId = ownerId,
        ),
    )

    suspend fun delete(notificationId: String): Result<Unit> = doRequest(
        urlPostfixPath = notificationId,
        method = HttpMethod.Put,
    )

    suspend fun read(notificationId: String): Result<NotificationResponse> = doRequest(
        urlPostfixPath = notificationId,
        method = HttpMethod.Get,
    )

    suspend fun readAll(): Result<List<NotificationResponse>> =
        doRequest<List<NotificationResponse>>(
            method = HttpMethod.Get,
        )
}