package com.spoonofcode.feature.profile.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.feature.profile.data.model.ProfileResponse
import io.ktor.http.HttpMethod

class RemoteUserDataSource : RemoteBaseDataSource(
    collectionName = "users"
) {
    suspend fun readUserProfile(): Result<ProfileResponse> = doRequest(
        urlPostfixPath = "profile",
        method = HttpMethod.Get,
    )
}