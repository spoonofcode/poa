package com.spoonofcode.feature.tac.data.repository

import com.spoonofcode.feature.tac.data.local.LocalTACDataSource
import com.spoonofcode.feature.tac.domain.model.TACSettings
import com.spoonofcode.feature.tac.domain.repository.TACRepository

internal class TACRepositoryImpl(
    private val localTACDataSource: LocalTACDataSource,
) : TACRepository {

    override suspend fun getTACSettings(): Result<TACSettings> = runCatching {
        localTACDataSource.getTACSettings()
    }

    override suspend fun setTACSettings(
        isPrivacyPolicyAccepted: Boolean,
        isTermsAndConditionsAccepted: Boolean,
    ): Result<Unit> = runCatching {
        localTACDataSource.setTACSettings(
            isPrivacyPolicyAccepted = isPrivacyPolicyAccepted,
            isTermsAndConditionsAccepted = isTermsAndConditionsAccepted,
        )
    }
}
