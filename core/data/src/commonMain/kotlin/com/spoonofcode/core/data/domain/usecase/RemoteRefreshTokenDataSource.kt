package com.spoonofcode.core.data.domain.usecase

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.core.data.network.RefreshRequest
import com.spoonofcode.core.data.network.RefreshResponse
import io.ktor.http.HttpMethod

// TODO #109 Fix after migration to feature modules
class RemoteRefreshTokenDataSource() : RemoteBaseDataSource(
    collectionName = "api/refresh",
) {
    suspend fun create(
        refreshToken: String,
    ): Result<RefreshResponse> = doRequest(
        method = HttpMethod.Companion.Post,
        requestBody = RefreshRequest(
            refreshToken = refreshToken,
        ),
        markAsRefreshTokenRequest = true,
    )
}