package com.spoonofcode.feature.tac.data

import com.spoonofcode.feature.tac.data.local.LocalTACDataSource
import com.spoonofcode.feature.tac.domain.model.TACSettings
import com.spoonofcode.feature.tac.domain.repository.TACRepository

class TACRepositoryImpl(
    private val localTACDataSource: LocalTACDataSource,
) : TACRepository {

    override suspend fun getTACSettings(): TACSettings {
        return localTACDataSource.getTACSettings()
    }

    override suspend fun setTACSettings(
        isPrivacyPolicyAccepted: Boolean,
        isTermsAndConditionsAccepted: Boolean,
    ) {
        localTACDataSource.setTACSettings(
            isPrivacyPolicyAccepted = isPrivacyPolicyAccepted,
            isTermsAndConditionsAccepted = isTermsAndConditionsAccepted,
        )
    }
}
