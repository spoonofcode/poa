package com.spoonofcode.feature.profile.data.repository

import com.spoonofcode.feature.profile.data.mappers.toProfile
import com.spoonofcode.feature.profile.data.remote.RemoteProfileDataSource
import com.spoonofcode.feature.profile.domain.model.Profile
import com.spoonofcode.feature.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val remoteProfileDataSource: RemoteProfileDataSource,
) : ProfileRepository {

    override suspend fun readUserProfile(): Result<Profile> =
        remoteProfileDataSource.readUserProfile().map { it.toProfile() }

    override suspend fun updateUserProfile(): Result<Unit> {
        TODO("Not yet implemented")
    }
}