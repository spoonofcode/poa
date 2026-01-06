package com.spoonofcode.poa.feature.login.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.login.data.remote.model.LoginGoogleRequest
import com.spoonofcode.poa.feature.login.data.remote.model.LoginGoogleResponse
import io.ktor.http.HttpMethod

class RemoteLoginGoogleDataSource : RemoteBaseDataSource(
    collectionName = "api/login/google",
    sessionTokenRequired = false,
) {
    suspend fun create(
        googleUserToken: String,
    ): Result<LoginGoogleResponse> = doRequest(
        ktorBackend = false,
        method = HttpMethod.Post,
        requestBody = LoginGoogleRequest(
            googleUserToken = googleUserToken,
        ),
    )
}