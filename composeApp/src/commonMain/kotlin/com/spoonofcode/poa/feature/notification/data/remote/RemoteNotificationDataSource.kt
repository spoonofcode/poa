package com.spoonofcode.poa.feature.notification.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.notification.data.remote.model.NotificationRequest
import com.spoonofcode.poa.feature.notification.data.remote.model.NotificationResponse
import io.ktor.http.HttpMethod
import kotlinx.datetime.LocalDateTime

class RemoteNotificationDataSource : RemoteBaseDataSource(
    collectionName = "notifications",
) {
    suspend fun create(
        title: String,
        text: String,
        link: String?,
        imageUrl: String?,
        topics: List<String>,
        expirationDateTime: LocalDateTime?,
        alreadyRead: Boolean
    ): Result<Unit> = doRequest<Boolean>(
        method = HttpMethod.Post,
        requestBody = NotificationRequest(
            title = title,
            text = text,
            link = link,
            imageUrl = imageUrl,
            topics = topics,
            expirationDateTime = expirationDateTime,
            alreadyRead = alreadyRead,
        ),
    ).map { true }

     suspend fun read(notificationId: String): Result<NotificationResponse> = doRequest(
        urlPostfixPath = notificationId,
        method = HttpMethod.Get,
    )

     suspend fun readAll(): Result<List<NotificationResponse>> =
        doRequest<List<NotificationResponse>>(
            method = HttpMethod.Get,
        )
}