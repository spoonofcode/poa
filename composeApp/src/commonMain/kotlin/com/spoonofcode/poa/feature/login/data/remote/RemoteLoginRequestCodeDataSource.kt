package com.spoonofcode.poa.feature.login.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.login.data.remote.model.LoginRequestCodeRequest
import io.ktor.http.HttpMethod

class RemoteLoginRequestCodeDataSource() : RemoteBaseDataSource(
    collectionName = "api/login/request_code",
) {
    suspend fun create(
        email: String,
        recaptchaToken: String,
    ): Result<Unit> = doRequest(
        method = HttpMethod.Post,
        requestBody = LoginRequestCodeRequest(
            email = email,
            recaptchaToken = recaptchaToken,
        ),
    )
}