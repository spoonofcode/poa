package com.spoonofcode.poa.feature.profile.data

import com.spoonofcode.poa.feature.profile.data.model.User
import com.spoonofcode.poa.feature.profile.data.remote.RemoteUserDataSource
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository
import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType

class ProfileRepositoryImpl(
    private val remoteUserDataSource: RemoteUserDataSource,
) : ProfileRepository {
    override suspend fun read(userId: String): Result<User> {
        return remoteUserDataSource.read(userId)
    }

    override suspend fun readUserProfile(userId: String): Result<Profile> {
        TODO("Not yet implemented")
    }

    override suspend fun addRewardToUser(
        rewardType: RewardType,
        userId: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserRewardsPoints(userId: String): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDailyCheckInStatus(userId: String): Result<DailyCheckInStatus> {
        TODO("Not yet implemented")
    }
}