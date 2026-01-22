package com.spoonofcode.poa.feature.login.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.core.network.RefreshRequest
import com.spoonofcode.poa.core.network.RefreshResponse
import io.ktor.http.HttpMethod

class RemoteRefreshTokenDataSource() : RemoteBaseDataSource(
    collectionName = "api/refresh",
    sessionTokenRequired = false,
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