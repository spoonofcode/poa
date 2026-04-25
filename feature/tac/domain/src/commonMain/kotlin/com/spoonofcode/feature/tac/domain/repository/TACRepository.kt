package com.spoonofcode.feature.tac.domain.repository

import com.spoonofcode.feature.tac.domain.model.TACSettings

interface TACRepository {

    suspend fun getTACSettings(): TACSettings
    suspend fun setTACSettings(
        isPrivacyPolicyAccepted: Boolean,
        isTermsAndConditionsAccepted: Boolean,
    )
}
