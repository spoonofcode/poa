package com.spoonofcode.poa.feature.login.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.login.data.mappers.toLoginGoogle
import com.spoonofcode.poa.feature.login.data.remote.model.LoginGoogleResponse
import com.spoonofcode.poa.feature.login.domain.LoginGoogleDataSource
import com.spoonofcode.poa.feature.login.domain.model.LoginGoogle
import com.spoonofcode.poa.feature.login.domain.model.LoginGoogleRequest
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

class RemoteLoginGoogleDataSource : RemoteBaseDataSource(
//    collectionName = "api/login/google", //TODO #13 Switch to POA backend API
    collectionName = "login/google",
    sessionTokenRequired = false,
), LoginGoogleDataSource {
    override suspend fun create(
        googleUserToken: String,
    ): Result<LoginGoogle> = doRequest<LoginGoogleResponse>(
        method = HttpMethod.Post,
        requestBody = LoginGoogleRequest(
            googleUserToken = googleUserToken,
        ),
        responseTypeInfo = typeInfo<LoginGoogleResponse>()
    ).map { response -> response.toLoginGoogle() }
}