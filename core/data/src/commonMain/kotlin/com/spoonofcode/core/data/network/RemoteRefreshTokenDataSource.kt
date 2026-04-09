package com.spoonofcode.core.data.network

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import io.ktor.http.HttpMethod

class RemoteRefreshTokenDataSource : RemoteBaseDataSource(
    collectionName = "api/refresh",
) {
    suspend fun create(
        refreshToken: String,
    ): Result<RefreshResponse> = doRequest(
        method = HttpMethod.Post,
        requestBody = RefreshRequest(
            refreshToken = refreshToken,
        ),
        markAsRefreshTokenRequest = true,
    )
}