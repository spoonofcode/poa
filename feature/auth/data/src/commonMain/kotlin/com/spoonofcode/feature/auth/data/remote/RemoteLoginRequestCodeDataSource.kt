package com.spoonofcode.feature.auth.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.auth.data.remote.model.LoginRequestCodeRequest
import io.ktor.http.HttpMethod

class RemoteLoginRequestCodeDataSource : RemoteBaseDataSource(
    collectionName = "api/login/request_code",
) {
    suspend fun create(
        email: String,
        platform: String,
        recaptchaToken: String,
    ): Result<Unit> = doRequest(
        method = HttpMethod.Post,
        requestBody = LoginRequestCodeRequest(
            email = email,
            platform = platform,
            recaptchaToken = recaptchaToken,
        ),
    )
}