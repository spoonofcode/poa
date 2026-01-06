package com.spoonofcode.poa.feature.profile.data

import com.spoonofcode.poa.feature.profile.data.mappers.toProfile
import com.spoonofcode.poa.feature.profile.data.remote.RemoteUserDataSource
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val remoteUserDataSource: RemoteUserDataSource,
) : ProfileRepository {

    override suspend fun readUserProfile(userId: String): Result<Profile> {
        return remoteUserDataSource.readUserProfile(userId).map { it.toProfile() }
    }
}