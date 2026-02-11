package com.spoonofcode.feature.notification.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.feature.notification.data.remote.model.NotificationRequest
import com.spoonofcode.feature.notification.data.remote.model.NotificationResponse
import io.ktor.http.HttpMethod
import kotlinx.datetime.LocalDateTime

class RemoteNotificationDataSource : RemoteBaseDataSource(
    collectionName = "api/notifications",
) {
    suspend fun create(
        title: String,
        text: String,
        link: String?,
        topic: String,
        expirationDateTime: LocalDateTime?,
    ): Result<NotificationResponse> = doRequest(
        method = HttpMethod.Post,
        requestBody = NotificationRequest(
            title = title,
            text = text,
            link = link,
            topic = topic,
            expirationDateTime = expirationDateTime,
        ),
    )

    suspend fun update(
        notificationId: String,
        title: String,
        text: String,
        link: String?,
        topic: String,
        expirationDateTime: LocalDateTime?,
    ): Result<Unit> = doRequest(
        urlPostfixPath = notificationId,
        method = HttpMethod.Put,
        requestBody = NotificationRequest(
            title = title,
            text = text,
            link = link,
            topic = topic,
            expirationDateTime = expirationDateTime,
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