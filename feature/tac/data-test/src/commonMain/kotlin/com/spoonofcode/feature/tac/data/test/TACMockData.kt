package com.spoonofcode.feature.tac.data.test

import com.spoonofcode.feature.tac.domain.model.TACSettings

object TACMockData {
    val SETTINGS_1 =
        TACSettings(
            isPrivacyPolicyAccepted = true,
            isTermsAndConditionsAccepted = true,
            privacyPolicyLink = "https://www.example.com/privacy-policy",
            marketingLink = "https://www.example.com/marketing",
        )
}
