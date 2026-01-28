package com.spoonofcode.poa.feature.login.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.login.data.remote.model.LoginCodeRequest
import com.spoonofcode.poa.feature.login.data.remote.model.LoginResponse
import io.ktor.http.HttpMethod

class RemoteLoginCodeDataSource() : RemoteBaseDataSource(
    collectionName = "api/login/code",
) {
    suspend fun create(
        email: String,
        loginCode: String,
    ): Result<LoginResponse> = doRequest(
        method = HttpMethod.Post,
        requestBody = LoginCodeRequest(
            email = email,
            loginCode = loginCode,
        ),
    )
}