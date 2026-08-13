package com.spoonofcode.feature.tac.domain.model

data class TACSettings(
    val isPrivacyPolicyAccepted: Boolean,
    val isTermsAndConditionsAccepted: Boolean,
    val privacyPolicyLink: String,
    val marketingLink: String,
)