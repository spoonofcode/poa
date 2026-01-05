package com.spoonofcode.poa.feature.login.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.login.data.mappers.toLogin
import com.spoonofcode.poa.feature.login.data.remote.model.LoginRequestCodeRequest
import com.spoonofcode.poa.feature.login.data.remote.model.LoginResponse
import com.spoonofcode.poa.feature.login.domain.LoginRequestCodeDataSource
import com.spoonofcode.poa.feature.login.domain.model.Login
import io.ktor.http.HttpMethod

class RemoteLoginRequestCodeDataSource() : RemoteBaseDataSource(
    collectionName = "api/login/request_code",
    sessionTokenRequired = false,
), LoginRequestCodeDataSource {
    override suspend fun create(
        email: String,
        recaptchaToken: String,
    ): Result<Login> = doRequest<LoginResponse>(
        method = HttpMethod.Post,
        requestBody = LoginRequestCodeRequest(
            email = email,
            recaptchaToken = recaptchaToken,
        ),
    ).map { response -> response.toLogin() }
}