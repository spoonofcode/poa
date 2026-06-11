package com.spoonofcode.feature.partner.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.partner.data.model.PartnerNotificationRequest
import com.spoonofcode.feature.partner.data.model.PartnerNotificationResponse
import io.ktor.http.HttpMethod
import kotlin.time.Instant

class RemotePartnerNotificationDataSource : RemoteBaseDataSource(
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
    ): Result<Unit> = doRequest(
        method = HttpMethod.Post,
        requestBody = PartnerNotificationRequest(
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
        requestBody = PartnerNotificationRequest(
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
        method = HttpMethod.Delete,
    )

    suspend fun read(notificationId: String): Result<PartnerNotificationResponse> = doRequest(
        urlPostfixPath = notificationId,
        method = HttpMethod.Get,
    )

    suspend fun readAll(): Result<List<PartnerNotificationResponse>> = doRequest(
        method = HttpMethod.Get,
    )
}
