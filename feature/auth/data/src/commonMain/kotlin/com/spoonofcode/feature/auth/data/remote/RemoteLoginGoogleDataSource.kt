package com.spoonofcode.feature.auth.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.auth.data.remote.model.LoginGoogleRequest
import com.spoonofcode.feature.auth.data.remote.model.LoginGoogleResponse
import io.ktor.http.HttpMethod

internal class RemoteLoginGoogleDataSource :
    RemoteBaseDataSource(
        collectionName = "api/login/google",
    ) {
    suspend fun create(
        googleUserToken: String,
    ): Result<LoginGoogleResponse> =
        doRequest(
            method = HttpMethod.Post,
            requestBody =
                LoginGoogleRequest(
                    googleUserToken = googleUserToken,
                ),
        )
}
