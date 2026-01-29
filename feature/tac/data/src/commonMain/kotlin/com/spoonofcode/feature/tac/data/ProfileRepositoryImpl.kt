package com.spoonofcode.feature.profile.data

import com.spoonofcode.feature.profile.data.remote.RemoteUserDataSource
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val remoteUserDataSource: RemoteUserDataSource,
) : ProfileRepository {

    // TODO #99 Adopt poa backend
    override suspend fun readUserProfile(): Result<Profile> =
        Result.success(Profile(
            name = "Bartosz",
            numberOfProductsOwnedByUser = 4,
            customLink = "https://example.com",

        ))
//        remoteUserDataSource.readUserProfile().map { it.toProfile() }

    override suspend fun updateUserProfile(): Result<Unit> {
        TODO("Not yet implemented")
    }
}