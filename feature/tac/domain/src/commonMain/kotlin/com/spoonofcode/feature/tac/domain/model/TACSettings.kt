package com.spoonofcode.feature.tac.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TACSettings(
    val isPrivacyPolicyAccepted: Boolean = false,
    val isTermsAndConditionsAccepted: Boolean = false,
)