package com.spoonofcode.poa.feature.notification.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.notification.domain.model.Notification
import com.spoonofcode.poa.feature.notification.data.model.NotificationRequest
import com.spoonofcode.poa.feature.notification.domain.NotificationDataSource
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo
import kotlinx.datetime.LocalDateTime

class RemoteNotificationDataSource : RemoteBaseDataSource(
    collectionName = "notifications",
), NotificationDataSource {
    override suspend fun create(
        title: String,
        text: String,
        link: String?,
        imageUrl: String?,
        topics: List<String>,
        expirationDateTime: LocalDateTime?,
        alreadyRead: Boolean
    ): Result<Unit> = doRequest<Boolean>(
        method = HttpMethod.Companion.Post,
        requestBody = NotificationRequest(
            title = title,
            text = text,
            link = link,
            imageUrl = imageUrl,
            topics = topics,
            expirationDateTime = expirationDateTime,
            alreadyRead = alreadyRead,
        ),
        responseTypeInfo = typeInfo<Unit>()
    ).map { true }

    override suspend fun read(notificationId: String): Result<Notification> = doRequest(
        urlPostfixPath = notificationId,
        method = HttpMethod.Companion.Get,
        responseTypeInfo = typeInfo<Notification>()
    )

    override suspend fun readAll(): Result<List<Notification>> = doRequest(
        method = HttpMethod.Companion.Get,
        responseTypeInfo = typeInfo<List<Notification>>(),
    )
}