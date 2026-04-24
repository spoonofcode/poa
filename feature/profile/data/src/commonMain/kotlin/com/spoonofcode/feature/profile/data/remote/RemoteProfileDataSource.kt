package com.spoonofcode.feature.profile.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.profile.data.model.ProfileResponse
import io.ktor.http.HttpMethod

class RemoteProfileDataSource : RemoteBaseDataSource(
    collectionName = "api/profile"
) {
    suspend fun readUserProfile(): Result<ProfileResponse> = doRequest(
        method = HttpMethod.Get,
    )
}