package com.spoonofcode.feature.partner.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.partner.data.model.PartnerNotificationResponse
import io.ktor.http.HttpMethod

class RemotePartnerNotificationDataSource : RemoteBaseDataSource(
    collectionName = "api/notifications",
) {
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
