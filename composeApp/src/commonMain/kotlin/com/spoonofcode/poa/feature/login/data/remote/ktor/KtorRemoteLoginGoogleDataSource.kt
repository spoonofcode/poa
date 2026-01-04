package com.spoonofcode.poa.feature.login.data.remote.ktor

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.login.data.mappers.ktor.toKtorLoginGoogle
import com.spoonofcode.poa.feature.login.data.remote.ktor.model.KtorLoginGoogleRequest
import com.spoonofcode.poa.feature.login.data.remote.ktor.model.KtorLoginGoogleResponse
import com.spoonofcode.poa.feature.login.domain.ktor.KtorLoginGoogleDataSource
import com.spoonofcode.poa.feature.login.domain.ktor.model.KtorLoginGoogle
import io.ktor.http.HttpMethod

class KtorRemoteLoginGoogleDataSource : RemoteBaseDataSource(
    collectionName = "login/google",
    sessionTokenRequired = false,
), KtorLoginGoogleDataSource {
    override suspend fun create(
        googleUserToken: String,
    ): Result<KtorLoginGoogle> = doRequest<KtorLoginGoogleResponse>(
        method = HttpMethod.Post,
        requestBody = KtorLoginGoogleRequest(
            googleUserToken = googleUserToken,
        ),
    ).map { response -> response.toKtorLoginGoogle() }
}