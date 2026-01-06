package com.spoonofcode.poa.feature.login.data.remote.ktor

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.login.data.remote.ktor.model.KtorLoginGoogleRequest
import com.spoonofcode.poa.feature.login.data.remote.model.LoginResponse
import io.ktor.http.HttpMethod

class KtorRemoteLoginGoogleDataSource : RemoteBaseDataSource(
    collectionName = "login/google",
    sessionTokenRequired = false,
) {
    suspend fun create(
        googleUserToken: String,
    ): Result<LoginResponse> = doRequest(
        method = HttpMethod.Post,
        requestBody = KtorLoginGoogleRequest(
            googleUserToken = googleUserToken,
        ),
    )
}