package com.spoonofcode.poa.feature.login.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.login.data.mappers.toLogin
import com.spoonofcode.poa.feature.login.data.remote.model.LoginCodeRequest
import com.spoonofcode.poa.feature.login.data.remote.model.LoginResponse
import com.spoonofcode.poa.feature.login.domain.LoginCodeDataSource
import com.spoonofcode.poa.feature.login.domain.model.Login
import io.ktor.http.HttpMethod

class RemoteLoginCodeDataSource() : RemoteBaseDataSource(
    collectionName = "api/login/code",
    sessionTokenRequired = false,
), LoginCodeDataSource {
    override suspend fun create(
        email: String,
        loginCode: String,
    ): Result<Login> = doRequest<LoginResponse>(
        method = HttpMethod.Post,
        requestBody = LoginCodeRequest(
            email = email,
            loginCode = loginCode,
        ),
    ).map { response -> response.toLogin() }
}