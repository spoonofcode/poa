package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.core.model.LoginGoogle
import com.spoonofcode.poa.core.model.LoginGoogleRequest
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

interface LoginGoogleRepository {
    suspend fun create(googleUserToken: String): Result<LoginGoogle>
}

class LoginGoogleRemoteDataSource : RemoteBaseDataSource(
//    collectionName = "api/login/google", //TODO #13 Switch to POA backend API
    collectionName = "login/google",
    sessionTokenRequired = false,
), LoginGoogleRepository {
    override suspend fun create(
        googleUserToken: String,
    ): Result<LoginGoogle> = doRequest(
        method = HttpMethod.Post,
        requestBody = LoginGoogleRequest(
            googleUserToken = googleUserToken,
        ),
        responseTypeInfo = typeInfo<LoginGoogle>()
    )
}