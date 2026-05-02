package com.spoonofcode.feature.tac.domain.model

data class TACSettings(
    val isPrivacyPolicyAccepted: Boolean = false,
    val isTermsAndConditionsAccepted: Boolean = false,
)