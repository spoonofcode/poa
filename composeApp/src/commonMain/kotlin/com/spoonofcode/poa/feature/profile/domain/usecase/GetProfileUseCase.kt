package com.spoonofcode.poa.feature.profile.domain.usecase

import com.spoonofcode.poa.core.data.repository.UserDataSource
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.core.network.SessionManager

class GetProfileUseCase(
    private val userDataSource: UserDataSource,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<Profile> = userDataSource.readUserProfile(
        userId = sessionManager.getSessionUserId()
    )
}