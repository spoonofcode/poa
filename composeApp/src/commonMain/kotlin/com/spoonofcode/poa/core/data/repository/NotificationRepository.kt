package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.data.model.NotificationRequest
import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo
import kotlinx.datetime.LocalDateTime

@OpenForMokkery
class NotificationRepository : BaseRepository(
    collectionName = "notifications",
) {
    suspend fun create(
        title: String,
        text: String,
        link: String? = null,
        imageUrl: String? = null,
        topics: List<String>,
        expirationDateTime: LocalDateTime? = null,
        alreadyRead: Boolean = false,
    ): Result<Unit> = doRequest(
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
        responseTypeInfo = typeInfo<Unit>()
    )

    suspend fun read(
        notificationId: String,
    ): Result<Notification> = doRequest(
        urlPostfixPath = notificationId,
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<Notification>()
    )

    suspend fun readAll(): Result<List<Notification>> = doRequest(
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<Notification>>(),
    )
}