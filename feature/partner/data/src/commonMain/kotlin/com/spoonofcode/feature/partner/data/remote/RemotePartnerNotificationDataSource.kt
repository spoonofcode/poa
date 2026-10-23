package com.spoonofcode.feature.partner.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.partner.data.model.PartnerNotificationRequest
import com.spoonofcode.feature.partner.data.model.PartnerNotificationResponse
import io.ktor.http.HttpMethod
import kotlin.time.Instant

internal class RemotePartnerNotificationDataSource :
    RemoteBaseDataSource(
        collectionName = "api",
    ) {
    suspend fun create(
        title: String,
        text: String,
        link: String?,
        topics: List<String>,
        deliveryDateTime: Instant,
        expirationDateTime: Instant,
        ownerId: String,
    ): Result<Unit> =
        doRequest(
            urlPostfixPath = "notifications",
            method = HttpMethod.Post,
            requestBody =
                PartnerNotificationRequest(
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
    ): Result<Unit> =
        doRequest(
            urlPostfixPath = "notifications/$notificationId",
            method = HttpMethod.Put,
            requestBody =
                PartnerNotificationRequest(
                    title = title,
                    text = text,
                    link = link,
                    topics = topics,
                    deliveryDateTime = deliveryDateTime,
                    expirationDateTime = expirationDateTime,
                    ownerId = ownerId,
                ),
        )

    suspend fun delete(notificationId: String): Result<Unit> =
        doRequest(
            urlPostfixPath = "notifications/$notificationId",
            method = HttpMethod.Delete,
        )

    suspend fun read(notificationId: String): Result<PartnerNotificationResponse> =
        doRequest(
            urlPostfixPath = "notifications/$notificationId",
            method = HttpMethod.Get,
        )

    suspend fun readAll(): Result<List<PartnerNotificationResponse>> =
        doRequest(
            urlPostfixPath = "partners/me/notifications",
            method = HttpMethod.Get,
        )
}
