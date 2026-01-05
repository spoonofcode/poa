package com.spoonofcode.poa.feature.login.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.login.data.mappers.toRegister
import com.spoonofcode.poa.feature.login.data.remote.model.RegisterRequest
import com.spoonofcode.poa.feature.login.data.remote.model.RegisterResponse
import com.spoonofcode.poa.feature.login.domain.model.Register
import io.ktor.http.HttpMethod

class RemoteRegisterDataSource : RemoteBaseDataSource(
    collectionName = "register",
    sessionTokenRequired = false,
) {
    suspend fun create(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Result<Register> = doRequest<RegisterResponse>(
        method = HttpMethod.Post,
        requestBody = RegisterRequest(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
        ),
    ).map { response -> response.toRegister() }
}