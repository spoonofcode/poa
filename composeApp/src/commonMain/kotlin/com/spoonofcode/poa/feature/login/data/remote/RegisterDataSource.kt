package com.spoonofcode.poa.feature.login.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.core.data.model.RegisterRequest
import com.spoonofcode.poa.feature.login.domain.RegisterDataSource
import com.spoonofcode.poa.feature.login.domain.model.Register
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

class RegisterRemoteDataSource : RemoteBaseDataSource(
    collectionName = "register",
    sessionTokenRequired = false,
), RegisterDataSource {
    override suspend fun create(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Result<Register> = doRequest(
        method = HttpMethod.Post,
        requestBody = RegisterRequest(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
        ),
        responseTypeInfo = typeInfo<Register>()
    )
}