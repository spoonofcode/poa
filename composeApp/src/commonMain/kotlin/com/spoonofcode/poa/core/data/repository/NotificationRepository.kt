package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.model.NotificationRequest
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

@OpenForMokkery
class NotificationRepository : BaseRepository(
    collectionName = "notifications",
) {
    suspend fun create(
        notification: NotificationRequest,
    ): Boolean {
        doRequest<Unit>(
            method = HttpMethod.Post,
            requestBody = notification,
            responseTypeInfo = typeInfo<Unit>()
        )
        return true
    }

    suspend fun read(
        notificationId: String,
    ): Notification {
        return doRequest(
            urlPostfixPath = notificationId,
            method = HttpMethod.Get,
            responseTypeInfo = typeInfo<Notification>()
        )
    }

    suspend fun readAll(): List<Notification> {
        return doRequest(
            method = HttpMethod.Get,
            responseTypeInfo = typeInfo<List<Notification>>(),
        )
    }
}