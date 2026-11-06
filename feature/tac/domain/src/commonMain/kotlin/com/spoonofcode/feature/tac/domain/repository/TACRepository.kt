package com.spoonofcode.feature.tac.domain.repository

import com.spoonofcode.feature.tac.domain.model.TACSettings

interface TACRepository {
    suspend fun getTACSettings(): Result<TACSettings>

    suspend fun setTACSettings(
        isPrivacyPolicyAccepted: Boolean,
        isTermsAndConditionsAccepted: Boolean,
    ): Result<Unit>
}
