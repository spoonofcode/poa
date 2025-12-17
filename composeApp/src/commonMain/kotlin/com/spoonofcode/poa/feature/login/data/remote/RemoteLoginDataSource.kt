package com.spoonofcode.poa.feature.login.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.login.data.remote.model.LoginRequest
import com.spoonofcode.poa.feature.login.data.mappers.toLogin
import com.spoonofcode.poa.feature.login.data.remote.model.LoginResponse
import com.spoonofcode.poa.feature.login.domain.LoginDataSource
import com.spoonofcode.poa.feature.login.domain.model.Login
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

class RemoteLoginDataSource() : RemoteBaseDataSource(
    collectionName = "login",
    sessionTokenRequired = false,
), LoginDataSource {
    override suspend fun create(
        email: String,
        password: String
    ): Result<Login> = doRequest<LoginResponse>(
        method = HttpMethod.Post,
        requestBody = LoginRequest(
            email = email,
            password = password,
        ),
        responseTypeInfo = typeInfo<LoginResponse>()
    ).map { response -> response.toLogin() }
}