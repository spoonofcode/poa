package com.spoonofcode.core.network.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.core.network.model.RefreshRequest
import com.spoonofcode.core.network.model.RefreshResponse
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