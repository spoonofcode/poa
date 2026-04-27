package com.spoonofcode.feature.auth.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.auth.data.remote.model.LoginCodeRequest
import com.spoonofcode.feature.auth.data.remote.model.LoginCodeResponse
import io.ktor.http.HttpMethod

class RemoteLoginCodeDataSource : RemoteBaseDataSource(
    collectionName = "api/login/code",
) {
    suspend fun create(
        email: String,
        loginCode: String,
    ): Result<LoginCodeResponse> = doRequest(
        method = HttpMethod.Post,
        requestBody = LoginCodeRequest(
            email = email,
            loginCode = loginCode,
        ),
    )
}